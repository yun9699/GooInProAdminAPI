package org.gooinpro.gooinproadminapi.employer.resptiroty;

import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.employer.resptiroty.search.EmployerSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<EmployerEntity, Long>, EmployerSearch {

    Integer countByEdelete(boolean edelete);    //edelete 칼럼에 따른 count
}
