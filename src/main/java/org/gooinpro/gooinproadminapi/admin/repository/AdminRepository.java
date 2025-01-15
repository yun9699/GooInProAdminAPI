package org.gooinpro.gooinproadminapi.admin.repository;

import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.admin.repository.search.AdminSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long>, AdminSearch {
}