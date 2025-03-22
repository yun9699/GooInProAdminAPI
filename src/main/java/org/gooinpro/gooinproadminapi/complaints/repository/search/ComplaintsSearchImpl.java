package org.gooinpro.gooinproadminapi.complaints.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.complaints.domain.ComplaintsEntity;
import org.gooinpro.gooinproadminapi.complaints.domain.QComplaintsEntity;
import org.gooinpro.gooinproadminapi.complaints.domain.QComplaintsImageEntity;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsDetailDTO;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ComplaintsSearchImpl extends QuerydslRepositorySupport implements ComplaintsSearch {

    public ComplaintsSearchImpl() {
        super(ComplaintsEntity.class);
    }


    @Override
    public PageResponseDTO<ComplaintsListDTO> complaintsList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("cno").descending());

        QComplaintsEntity complaints = QComplaintsEntity.complaintsEntity;

        JPQLQuery<ComplaintsEntity> query = from(complaints);

        query.where(complaints.cdelete.eq(false));

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<ComplaintsListDTO> dtojpqlQuery = query.select(
                Projections.bean(ComplaintsListDTO.class,
                        complaints.cno,
                        complaints.eno.ename,
                        complaints.pno.pname,
                        complaints.ctitle,
                        complaints.cregdate,
                        complaints.ccheckedTime,
                        complaints.cdelete)
        );

        List<ComplaintsListDTO> dtoList = dtojpqlQuery.fetch();

        long total = dtojpqlQuery.fetchCount();

        return PageResponseDTO.<ComplaintsListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public ComplaintsDetailDTO getComplaintsDetail(Long cno) {

        QComplaintsEntity complaints = QComplaintsEntity.complaintsEntity;
        QComplaintsImageEntity image = QComplaintsImageEntity.complaintsImageEntity;

        // complaintsEntity와 complaintsImageEntity를 조인
        JPQLQuery<ComplaintsEntity> query = from(complaints);

        // LEFT JOIN 추가
        query.leftJoin(image).on(image.cno.eq(complaints));

        query.where(complaints.cno.eq(cno));
        query.where(complaints.cdelete.eq(false));

        JPQLQuery<ComplaintsDetailDTO> dtojpqlQuery = query.select(
                Projections.bean(ComplaintsDetailDTO.class,
                        complaints.cno,
                        complaints.eno.ename,
                        complaints.pno.pname,
                        image.cifilename,
                        complaints.canswer,
                        complaints.ctitle,
                        complaints.ccontent,
                        complaints.ccheckedTime,
                        complaints.cdelete,
                        complaints.cregdate)
        ).groupBy(complaints.cno);

        ComplaintsDetailDTO dto = dtojpqlQuery.fetchOne();

        return dto;
    }

    @Override
    public PageResponseDTO<ComplaintsListDTO> complaintsTFList(Boolean cstatus, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("cno").descending());

        QComplaintsEntity complaints = QComplaintsEntity.complaintsEntity;

        JPQLQuery<ComplaintsEntity> query = from(complaints);

        query.where(complaints.cdelete.eq(false));

        if (cstatus != null) {
            query.where(complaints.cstatus.eq(cstatus));
        }

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<ComplaintsListDTO> dtojpqlQuery = query.select(
                Projections.bean(ComplaintsListDTO.class,
                        complaints.cno,
                        complaints.eno.ename,
                        complaints.pno.pname,
                        complaints.ctitle,
                        complaints.cregdate,
                        complaints.ccheckedTime,
                        complaints.cdelete)
        );

        List<ComplaintsListDTO> dtoList = dtojpqlQuery.fetch();

        long total = dtojpqlQuery.fetchCount();

        return PageResponseDTO.<ComplaintsListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }



}
