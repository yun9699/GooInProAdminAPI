package org.gooinpro.gooinproadminapi.complaints.repository;

import org.gooinpro.gooinproadminapi.complaints.domain.ComplaintsEntity;
import org.gooinpro.gooinproadminapi.complaints.repository.search.ComplaintsSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComplaintsRepository extends JpaRepository<ComplaintsEntity, Long>, ComplaintsSearch {

    @Query("SELECT COUNT(*) FROM ComplaintsEntity WHERE cstatus=false")
    Long countByFStatus();

    @Query("SELECT COUNT(*) FROM ComplaintsEntity WHERE cstatus=true")
    Long countByTStatus();

}
