package com.netcracker.event.controller;

import com.netcracker.event.domain.Organization;
import com.netcracker.event.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrganizationController {
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService=organizationService;
    }

    @PostMapping(path = "/save/organization")
    public ResponseEntity saveOrganization(@RequestBody Organization organization) {
        organizationService.saveOrganization(organization);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/organization/{organization_id}")
    public ResponseEntity getOrganizationById(@PathVariable(value = "organization_id") UUID organizationId){
        return ResponseEntity.ok().body(organizationService.findByOrganizationId(organizationId));
    }

    @GetMapping(path = "/organization/{organization_id}/info")
    public ResponseEntity getOrganizationInfo(@PathVariable(value = "organization_id") UUID organizationId){
        return ResponseEntity.ok().body(organizationService.getOrganizationInfo(organizationId));
    }

    @PatchMapping(path = "/update/organization")
    public ResponseEntity updateOrganization(@RequestBody Organization organization){
        organizationService.saveOrganization(organization);
        return ResponseEntity.ok().build();
    }
}