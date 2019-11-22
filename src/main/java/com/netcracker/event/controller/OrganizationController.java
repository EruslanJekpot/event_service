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
    public ResponseEntity updateOrganization(@RequestBody Organization organization,
                                             @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("uploaded")));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            System.out.println("File is empty");
        }
        organizationService.saveOrganization(organization);
        return ResponseEntity.ok().build();
    }
}