package com.example.servicea.controller;

import com.example.servicea.service.EmployeeDetailService;
import com.example.servicea.vo.EmployeeDetailVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@RestController
public class StatusController {

    private final DataSource primaryDataSource;
    private final EmployeeDetailService employeeDetailService;

    public StatusController(DataSource primaryDataSource, EmployeeDetailService employeeDetailService) {
        this.primaryDataSource = primaryDataSource;
        this.employeeDetailService = employeeDetailService;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${service-b.url}")
    private String serviceBUrl;

    @GetMapping("/a/status")
    public String status() {
        String bStatus = restTemplate.getForObject(serviceBUrl + "/b/status", String.class);
        return "A OK | B: " + bStatus;
    }

    @GetMapping("/a/health")
    public String health() {
        return "200";
    }

    @GetMapping("/a/dbtest")
    public ResponseEntity<String> dbTest() {
        String sql = "SELECT 1";

        try (Connection conn = primaryDataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int value = rs.getInt(1);
                return ResponseEntity.ok("DB OK, result=" + value);
            } else {
                return ResponseEntity.internalServerError()
                        .body("DB FAIL: no rows");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("DB ERROR: " + e.getMessage());
        }
    }

    @GetMapping("/a/employee-detail")
    public ResponseEntity<EmployeeDetailVO> getEmployeeDetail() {
        String employeeId = "030473"; // 테스트용 하드코딩
        EmployeeDetailVO vo = employeeDetailService.getEmployeeDetailById(employeeId);
        if (vo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vo);
    }
}