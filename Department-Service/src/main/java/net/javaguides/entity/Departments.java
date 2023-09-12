package net.javaguides.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Department_Details")
public class Departments {
    @Id
    private int id;
    private String departmentName;
    private String departmentDesc;
    private String departmentCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDesc() {
        return departmentDesc;
    }

    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Departments(int id, String departmentName, String departmentDesc, String departmentCode) {
        this.id = id;
        this.departmentName = departmentName;
        this.departmentDesc = departmentDesc;
        this.departmentCode = departmentCode;
    }

    public Departments() {
        super();
    }
}
