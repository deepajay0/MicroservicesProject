package net.javaguides.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import net.javaguides.dto.ApiResponseDto;
import net.javaguides.dto.DepartmentDto;
import net.javaguides.dto.EmployeeDto;
import net.javaguides.dto.OrganizationDto;
import net.javaguides.entity.Employee;
import net.javaguides.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;
//    @Autowired
//    private APIClient apiClient;
//    private RestTemplate restTemplate;
    @Autowired
    private WebClient webClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getId(),employeeDto.getFirstName(),employeeDto.getLastName(),
                                        employeeDto.getEmail(),employeeDto.getDepartmentCode(),employeeDto.getOrganizationCode());
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto1 = new EmployeeDto(savedEmployee.getId(),savedEmployee.getFirstName(),
                                                    savedEmployee.getLastName(),savedEmployee.getEmail(),savedEmployee.getDepartmentCode(),savedEmployee.getOrganizationCode());

        return employeeDto1;
    }

//    @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}",fallbackMethod ="getDefaultDepartment" )
    @Override
    public ApiResponseDto getEmployee(long EmpId) {
        LOGGER.info("inside get employee by id method");
        Employee savedEmployee = employeeRepository.findById(EmpId).get();
       // ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + savedEmployee.getDepartmentCode(), DepartmentDto.class);
      //  DepartmentDto departmentDto = responseEntity.getBody();
        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/" + savedEmployee.getDepartmentCode()).retrieve().bodyToMono(DepartmentDto.class).block();
        OrganizationDto organizationDto = webClient.get().uri("http://localhost:8083/api/organizations/" + savedEmployee.getOrganizationCode())
                .retrieve().bodyToMono(OrganizationDto.class).block();
      //  DepartmentDto departmentDto = apiClient.getDepartment(savedEmployee.getDepartmentCode());
        EmployeeDto employeeDto = new EmployeeDto(savedEmployee.getId(),savedEmployee.getFirstName(),savedEmployee.getLastName(),
                                                    savedEmployee.getEmail(),savedEmployee.getDepartmentCode(), savedEmployee.getOrganizationCode());
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);
        return apiResponseDto;
    }
    public ApiResponseDto getDefaultDepartment(long EmpId,Exception exception) {
        LOGGER.info("inside fallback method");
        Employee savedEmployee = employeeRepository.findById(EmpId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDesc("Research and development department");



        EmployeeDto employeeDto = new EmployeeDto(savedEmployee.getId(),savedEmployee.getFirstName(),savedEmployee.getLastName(),
                savedEmployee.getEmail(),savedEmployee.getDepartmentCode(), savedEmployee.getOrganizationCode());
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;

    }
}
