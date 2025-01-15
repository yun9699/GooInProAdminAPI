package org.gooinpro.gooinproadminapi.parttimer.repository;

import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;
import org.gooinpro.gooinproadminapi.parttimer.repository.search.PartTimerSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartTimerRepository extends JpaRepository<PartTimerEntity, Long>, PartTimerSearch {
}
