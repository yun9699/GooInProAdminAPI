package org.gooinpro.gooinproadminapi.customersupport.repository.qna;

import org.gooinpro.gooinproadminapi.customersupport.domain.QNAEntity;
import org.gooinpro.gooinproadminapi.customersupport.repository.qna.search.QNASearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QNARepository extends JpaRepository<QNAEntity, Long>, QNASearch {

    @Query("SELECT COUNT(*) FROM QNAEntity WHERE qstatus=false")
    Long countByFStatus();

    @Query("SELECT COUNT(*) FROM QNAEntity WHERE qstatus=true")
    Long countByTStatus();
}
