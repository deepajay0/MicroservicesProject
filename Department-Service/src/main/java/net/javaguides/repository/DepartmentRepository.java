package net.javaguides.repository;

import net.javaguides.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DepartmentRepository extends JpaRepository<Departments,Integer> {
    Departments findByDepartmentCode(String departmentCode);
}
