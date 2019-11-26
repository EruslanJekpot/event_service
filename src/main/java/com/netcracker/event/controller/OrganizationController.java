package com.netcracker.event.controller;

import com.netcracker.event.domain.Organization;
import com.netcracker.event.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class OrganizationController {
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService=organizationService;
    }

    @PostMapping(path = "/save/organization")
    public ResponseEntity saveOrganization(Organization organization) {
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
    public ResponseEntity updateOrganization(@RequestHeader("uid") String userId, @RequestBody Organization org){
        Organization organization = organizationService.getOrganizationByUser(userId);
        organization.setEmail(org.getEmail());
        organization.setInfo(org.getInfo());
        organization.setPhone(org.getPhone());
        organization.setImage(org.getImage());
        organizationService.saveOrganization(organization);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/organization/profile")
    public ResponseEntity getProfile(@RequestHeader("uid") String uid) {
        Organization organization = organizationService.getOrganizationByUser(uid);
        return ResponseEntity.status(HttpStatus.OK).body(organization);
    }
}