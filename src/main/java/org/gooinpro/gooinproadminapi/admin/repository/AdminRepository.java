package org.gooinpro.gooinproadminapi.admin.repository;

import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.admin.repository.search.AdminSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long>, AdminSearch{
    Optional<AdminEntity> findById(Long admno);
    Optional<AdminEntity> findByAdmid(String admid);
    Optional<AdminEntity> findByAdmidAndAdmdelete(String admid, boolean admdelete);
}