package net.javaguides.service;

import net.javaguides.dto.ApiResponseDto;
import net.javaguides.dto.EmployeeDto;

public interface EmployeeService {
     EmployeeDto saveEmployee(EmployeeDto employeeDto);
     ApiResponseDto getEmployee(long EmpId);
}
