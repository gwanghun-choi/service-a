package com.example.servicea.service.impl;

import org.springframework.stereotype.Service;

import com.example.servicea.mapper.primary.EmployeeDetailMapper;
import com.example.servicea.service.EmployeeDetailService;
import com.example.servicea.vo.EmployeeDetailVO;

@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService {

    private final EmployeeDetailMapper employeeDetailMapper;

    public EmployeeDetailServiceImpl(EmployeeDetailMapper employeeDetailMapper) {
        this.employeeDetailMapper = employeeDetailMapper;
    }

    @Override
    public EmployeeDetailVO getEmployeeDetailById(String employeeId) {
        return employeeDetailMapper.selectEmployeeDetailById(employeeId);
    }
}
