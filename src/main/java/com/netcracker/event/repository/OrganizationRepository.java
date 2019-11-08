package com.netcracker.event.repository;


import com.netcracker.event.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    Organization findByOrganizationId(Long id);
    @Modifying
    @Transactional
    @Query("update Organization organization set organization.organizationId = :org_id, organization.name = :name, organization.email = :email, " +
            "organization.phone = :phone, organization.info = :info where organization.organizationId = :org_id")
    void updateOrganization(@Param("org_id") Long id, @Param("name") String name, @Param("email") String email,
                              @Param("phone") String phone, @Param("info") String info);
}
