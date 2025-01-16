package org.gooinpro.gooinproadminapi.parttimer.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.QPartTimerDocEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.QPartTimerEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.QPartTimerImageEntity;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerDetailDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


@Log4j2
public class PartTimerSearchImpl extends QuerydslRepositorySupport implements PartTimerSearch {
    public PartTimerSearchImpl() {
        super(PartTimerEntity.class);
    }

    @Override
    public PageResponseDTO<PartTimerListDTO> getPartTimerList(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("pno").descending());

        QPartTimerEntity partTimer = QPartTimerEntity.partTimerEntity;

        JPQLQuery<PartTimerEntity> query = from(partTimer);

        query.where(partTimer.pno.gt(0));
        query.where(partTimer.pdelete.isFalse());

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<PartTimerListDTO> dtojpqlQuery = query.select(
                Projections.bean(PartTimerListDTO.class,
                        partTimer.pno,
                        partTimer.pbirth,
                        partTimer.pgender,
                        partTimer.pemail,
                        partTimer.pregdate,
                        partTimer.pname,
                        partTimer.proadAddress)
        );

        List<PartTimerListDTO> dtoList = dtojpqlQuery.fetch();

        long total = query.fetchCount();

        return PageResponseDTO.<PartTimerListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public PartTimerDetailDTO partTimerDetail(Long pno) {

        QPartTimerEntity partTimer = QPartTimerEntity.partTimerEntity;
        QPartTimerDocEntity partTimerDoc = QPartTimerDocEntity.partTimerDocEntity;
        QPartTimerImageEntity partTimerImage = QPartTimerImageEntity.partTimerImageEntity;

        JPQLQuery<PartTimerEntity> query = from(partTimer);

        query.leftJoin(partTimerDoc).on(partTimerDoc.pno.eq(partTimer));
        query.leftJoin(partTimerImage).on(partTimerImage.pno.eq(partTimer));

        query.where(partTimer.pno.eq(pno));
        query.where(partTimer.pdelete.isFalse());

        JPQLQuery<PartTimerDetailDTO> dtoQuery = query.select(
                Projections.bean(
                        PartTimerDetailDTO.class,
                        partTimer.pno,
                        partTimer.pemail,
                        partTimer.ppw,
                        partTimer.pname,
                        partTimerImage.pifilename,
                        partTimerDoc.pdifilename,
                        partTimer.pbirth,
                        partTimer.pgender,
                        partTimer.pdelete,
                        partTimer.pregdate,
                        partTimer.proadAddress,
                        partTimer.pdetailAddress
                )
        );


        PartTimerDetailDTO result = dtoQuery.fetchOne();

        if (result == null) {
            log.warn("해당 근로자가 없습니다.: {}", pno);
            return null;
        }

        return result;
    }

}
