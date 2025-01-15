package org.gooinpro.gooinproadminapi.customersupport.repository.qna;

import org.gooinpro.gooinproadminapi.customersupport.domain.QNAEntity;
import org.gooinpro.gooinproadminapi.customersupport.repository.qna.search.QNASearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QNARepository extends JpaRepository<QNAEntity, Long>, QNASearch {
}
