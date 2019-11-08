package com.netcracker.event.controller;

import com.netcracker.event.domain.Organization;
import com.netcracker.event.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrganizationController {
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService=organizationService;
    }

    @PostMapping(path = "/add/org")
    public void addOrganization(@RequestBody Organization organization){
        organizationService.addOrganization(organization);
    }

    @GetMapping(path = "/org/{org_id}")
    public Organization getOrganizationById(@PathVariable(value = "org_id") Long organizationId){
        return organizationService.findByOrganizationId(organizationId);
    }

    @GetMapping(path = "/org/{org_id}/info")
    public String getOrganizationInfo(@PathVariable(value = "org_id") Long organizationId){
        return organizationService.getOrganizationInfo(organizationId);
    }

    @PatchMapping(path = "/org/update")
    public void updateOrganization(@RequestBody Organization organization){
        organizationService.updateOrganization(organization);
    }
}