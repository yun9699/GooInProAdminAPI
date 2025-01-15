package org.gooinpro.gooinproadminapi.employer.repository;

import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.employer.repository.search.EmployerSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<EmployerEntity, Long>, EmployerSearch {

    Optional<EmployerEntity> findByEno(Long eno);


    Integer countByEdelete(boolean edelete);    //edelete 칼럼에 따른 count
}
