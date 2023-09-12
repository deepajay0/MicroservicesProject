package net.javaguides.controller;

import net.javaguides.dto.DepartmentDto;
import net.javaguides.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<DepartmentDto>(savedDepartmentDto,HttpStatus.CREATED );
    }
    @GetMapping("{code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("code")String departCode){
        DepartmentDto department = departmentService.getDepartment(departCode);
        return new ResponseEntity<>(department,HttpStatus.OK);
    }
}
