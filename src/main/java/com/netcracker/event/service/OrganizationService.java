package com.netcracker.event.service;

import com.netcracker.event.domain.Organization;
import com.netcracker.event.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository=organizationRepository;
    }

    public Organization findByOrganizationId(Long id){
        return this.organizationRepository.findByOrganizationId(id);
    }

    //??
    public String getOrganizationInfo(Long id){
        return this.organizationRepository.findByOrganizationId(id).getInfo();
    }

    public Boolean addOrganization(Organization organization) {
        try {
            organizationRepository.save(organization);
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при сохранении организации");
            return false;
        }
    }

    public Boolean updateOrganization(Organization organization) {
        try {
            organizationRepository.updateOrganization(organization.getOrganizationId(), organization.getName(),
                    organization.getEmail(), organization.getPhone(), organization.getInfo());
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при изменении данных организации");
            System.out.println(e);
            return false;
        }
    }
}