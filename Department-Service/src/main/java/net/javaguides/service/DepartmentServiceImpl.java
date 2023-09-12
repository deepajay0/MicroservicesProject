package net.javaguides.service;

import net.javaguides.dto.DepartmentDto;
import net.javaguides.entity.Departments;
import net.javaguides.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Departments departments = new Departments(departmentDto.getId(),departmentDto.getDepartmentName(),
                                                   departmentDto.getDepartmentDesc(),departmentDto.getDepartmentCode() );
        Departments savedDepartment = departmentRepository.save(departments);
        DepartmentDto saveddepartmentDto = new DepartmentDto(savedDepartment.getId(), savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDesc(), savedDepartment.getDepartmentCode());
        return saveddepartmentDto;
    }

    @Override
    public DepartmentDto getDepartment(String code) {
        Departments byDepartmentCode = departmentRepository.findByDepartmentCode(code);
        DepartmentDto departmentDto = new DepartmentDto(byDepartmentCode.getId(),byDepartmentCode.getDepartmentName(),byDepartmentCode.getDepartmentDesc(),byDepartmentCode.getDepartmentCode());
        return departmentDto;
    }
}
