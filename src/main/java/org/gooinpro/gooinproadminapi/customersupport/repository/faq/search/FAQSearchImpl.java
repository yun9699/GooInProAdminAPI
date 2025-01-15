package org.gooinpro.gooinproadminapi.customersupport.repository.faq.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.domain.FAQEntity;
import org.gooinpro.gooinproadminapi.customersupport.domain.QFAQEntity;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQDetailDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FAQSearchImpl extends QuerydslRepositorySupport implements FAQSearch {
    public FAQSearchImpl() {
        super(FAQEntity.class);
    }

    @Override
    public PageResponseDTO<FAQListDTO> faqList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("fno").descending());

        QFAQEntity faq = QFAQEntity.fAQEntity;
        JPQLQuery<FAQEntity> query = from(faq);

        query.where(faq.fdelete.eq(false));

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<FAQListDTO> dtojpqlQuery = query.select(
                Projections.bean(FAQListDTO.class,
                        faq.fno,
                        faq.admno.admname,
                        faq.ftitle,
                        faq.fcategory,
                        faq.fdelete)
        );

        List<FAQListDTO> dtoList = dtojpqlQuery.fetch();

        long total = query.fetchCount();

        return PageResponseDTO.<FAQListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }

    @Override
    public FAQDetailDTO faqDetail(Long fno) {

        QFAQEntity faq = QFAQEntity.fAQEntity;
        JPQLQuery<FAQEntity> query = from(faq);

        query.where(faq.fdelete.eq(false));
        query.where(faq.fno.eq(fno));

        JPQLQuery<FAQDetailDTO> dtojpqlQuery = query.select(
                Projections.bean(FAQDetailDTO.class,
                        faq.fno,
                        faq.admno.admname,
                        faq.ftitle,
                        faq.fcontent,
                        faq.fdelete,
                        faq.fcategory)
        );

        FAQDetailDTO dto = dtojpqlQuery.fetchOne();

        return dto;
    }
}
