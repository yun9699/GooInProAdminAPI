package org.gooinpro.gooinproadminapi.customersupport.repository.qna.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.domain.QNAEntity;
import org.gooinpro.gooinproadminapi.customersupport.domain.QQNAEntity;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNADetailDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNAListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class QNASearchImpl extends QuerydslRepositorySupport implements QNASearch {
    public QNASearchImpl() {
        super(QNAEntity.class);
    }

    @Override
    public PageResponseDTO<QNAListDTO> qnaList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("qno").descending());

        QQNAEntity qna = QQNAEntity.qNAEntity;

        JPQLQuery<QNAEntity> query = from(qna);

        query.where(qna.qno.gt(0));
        query.where(qna.qdelete.isFalse());

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<QNAListDTO> tupleQuery = query.select(
                Projections.bean(QNAListDTO.class,
                        qna.qno,
                        qna.admno.admname,
                        qna.pno.pname,
                        qna.qtitle,
                        qna.qdelete,
                        qna.qregdate,
                        qna.qmoddate,
                        qna.qstatus)
        );

        List<QNAListDTO> dtoList = tupleQuery.fetch();

        long total = tupleQuery.fetchCount();

        return PageResponseDTO.<QNAListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public QNADetailDTO qnaDetail(Long qno) {

        QQNAEntity qna = QQNAEntity.qNAEntity;

        JPQLQuery<QNAEntity> query = from(qna);

        query.where(qna.qno.eq(qno));
        query.where(qna.qdelete.isFalse());

        JPQLQuery<QNADetailDTO> tupleQuery = query.select(
                Projections.bean(QNADetailDTO.class,
                        qna.qno,
                        qna.qtitle,
                        qna.qcontent,
                        qna.qanswer,
                        qna.pno.pname,
                        qna.admno.admname,
                        qna.qstatus,
                        qna.qregdate,
                        qna.qmoddate)
        );

        QNADetailDTO dto = tupleQuery.fetchOne();

        return dto;
    }

    @Override
    public PageResponseDTO<QNAListDTO> qnaStatusList(Boolean status, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("qno").descending());

        QQNAEntity qna = QQNAEntity.qNAEntity;

        JPQLQuery<QNAEntity> query = from(qna);

        query.where(qna.qno.gt(0));
        query.where(qna.qdelete.isFalse());

        if (status != null) {
            query.where(qna.qstatus.eq(status));
        }

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<QNAListDTO> tupleQuery = query.select(
                Projections.bean(QNAListDTO.class,
                        qna.qno,
                        qna.admno.admname,
                        qna.pno.pname,
                        qna.qtitle,
                        qna.qdelete,
                        qna.qregdate,
                        qna.qmoddate,
                        qna.qstatus)
        );

        List<QNAListDTO> dtoList = tupleQuery.fetch();

        long total = tupleQuery.fetchCount();

        return PageResponseDTO.<QNAListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
