package org.gooinpro.gooinproadminapi.parttimer.repository.search;

import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.QPartTimerEntity;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class PartTimerSearchImpl extends QuerydslRepositorySupport implements PartTimerSearch {
    public PartTimerSearchImpl() {
        super(PartTimerEntity.class);
    }

    @Override
    public PageResponseDTO<PartTimerDTO> getPartTimerList(PageRequestDTO pageRequestDTO) {

        QPartTimerEntity partTimer = QPartTimerEntity.partTimerEntity;

        return null;
    }
}
