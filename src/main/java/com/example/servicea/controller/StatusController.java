package com.example.servicea.controller;

import com.example.servicea.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StatusController {

    private final TestMapper testMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${service-b.url}")
    private String serviceBUrl;

    @GetMapping("/a/status")
    public String status() {
        String bStatus = restTemplate.getForObject(serviceBUrl + "/b/status", String.class);
        return "[v3] A OK | B: " + bStatus;
    }

    @GetMapping("/a/health")
    public String health() {
        return "200";
    }

    @GetMapping("/a/test")
    public Map<String, Object> testDb() {
        return testMapper.selectDbInfo();
    }
}