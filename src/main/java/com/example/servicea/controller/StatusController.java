package com.example.servicea.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StatusController {

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
        System.out.println("Commit을 위한 테스트 Print, 추구 삭제");
        return "200";
    }
}