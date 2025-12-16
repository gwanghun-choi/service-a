package com.example.servicea.controller;

import com.example.servicea.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DbTestController {

    private final TestMapper testMapper;

    @GetMapping("/db/test")
    public Map<String, Object> testDb() {
        return testMapper.selectDbInfo();
    }
}
