package com.netcracker.event.controller;

import com.netcracker.event.domain.Organization;
import com.netcracker.event.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class OrganizationController {
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping(path = "/save/organization")
    public ResponseEntity saveOrganization(@RequestBody Organization organization) {
        long start, end;
        byte[] image;
        Organization existingOrg = organizationService.findByEmail(organization.getEmail());
        if (existingOrg != null) {
            return ResponseEntity.badRequest().body("email in use");
        }
        existingOrg = organizationService.findByName(organization.getName());
        if (existingOrg != null) {
            return ResponseEntity.badRequest().body("orgName in use");
        }
        try {
            image = organizationService.extractBytes("/static/org.jpg");
        } catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error loading image");
        }
        start = System.nanoTime();
        organization.setImage(image);
        organizationService.saveOrganization(organization);
        end = System.nanoTime();
        System.out.println("cold time " + String.format("%,12d",(end-start)) + " ns");
        System.out.println("warmed time " + String.format("%,12d",(end-start)) + " ns");
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/organization/{organization_user_id}")
    public ResponseEntity getOrganizationById(@PathVariable(value = "organization_user_id") String userId) {
        return ResponseEntity.ok().body(organizationService.getOrganizationByUser(userId));
    }

    @GetMapping(path = "/organization/{organization_id}/info")
    public ResponseEntity getOrganizationInfo(@PathVariable(value = "organization_id") UUID organizationId) {
        return ResponseEntity.ok().body(organizationService.getOrganizationInfo(organizationId));
    }

    @PatchMapping(path = "/update/organization")
    public ResponseEntity updateOrganization(@RequestHeader("uid") String userId, @RequestBody Organization org) {
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
