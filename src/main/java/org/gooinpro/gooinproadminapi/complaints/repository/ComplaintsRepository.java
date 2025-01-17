package org.gooinpro.gooinproadminapi.complaints.repository;

import org.gooinpro.gooinproadminapi.complaints.domain.ComplaintsEntity;
import org.gooinpro.gooinproadminapi.complaints.repository.search.ComplaintsSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintsRepository extends JpaRepository<ComplaintsEntity, Long>, ComplaintsSearch {
}
