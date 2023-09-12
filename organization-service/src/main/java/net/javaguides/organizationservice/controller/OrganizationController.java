package net.javaguides.organizationservice.controller;

import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    //build save organization rest api
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedorganizationDto = organizationService.saveOrganizationDto(organizationDto);
        return new ResponseEntity<>(savedorganizationDto,HttpStatus.CREATED);
    }

    @GetMapping("{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String organizationCode){
        OrganizationDto organizationByCodeDto = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organizationByCodeDto,HttpStatus.OK);
    }
}
