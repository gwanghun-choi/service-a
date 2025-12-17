// src/main/java/com/example/servicea/mapper/primary/EmployeeDetailMapper.java
package com.example.servicea.mapper.primary;

import org.apache.ibatis.annotations.Param;

import com.example.servicea.vo.EmployeeDetailVO;

public interface EmployeeDetailMapper {

    EmployeeDetailVO selectEmployeeDetailById(@Param("employeeId") String employeeId);
}
