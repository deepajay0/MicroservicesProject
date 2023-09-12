package net.javaguides.organizationservice.service;

import net.javaguides.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganizationDto(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);

}
