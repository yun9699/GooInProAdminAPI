package org.gooinpro.gooinproadminapi.employer.resptiroty.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.employer.domain.QEmployerEntity;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class EmployerSearchImpl extends QuerydslRepositorySupport implements EmployerSearch {

    public EmployerSearchImpl() {
        super(EmployerEntity.class);
    }

    private PageResponseDTO<EmployerListDTO> returnEmployerList(
            QEmployerEntity employer, JPQLQuery<EmployerEntity> query, PageRequestDTO pageRequestDTO
    ) {

        JPQLQuery<EmployerListDTO> tupleQuery = query.select(
                Projections.bean(EmployerListDTO.class,
                        employer.eno,
                        employer.eemail,
                        employer.ename,
                        employer.egender,
                        employer.ebirth,
                        employer.eregdate
                )
        );

        List<EmployerListDTO> dtoList = tupleQuery.fetch();
        long total = tupleQuery.fetchCount();

        return PageResponseDTO.<EmployerListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public PageResponseDTO<EmployerListDTO> approvedEmployerList(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("eno").descending());

        QEmployerEntity employer = QEmployerEntity.employerEntity;

        JPQLQuery<EmployerEntity> query = from(employer);

        query.where(employer.eno.gt(0));
        query.where(employer.edelete.isFalse());
        query.where(employer.eapproved.isTrue());

        this.getQuerydsl().applyPagination(pageable, query);

        return returnEmployerList(employer, query, pageRequestDTO);
    }

    @Override
    public PageResponseDTO<EmployerListDTO> notApprovedEmployerList(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("eno").descending());

        QEmployerEntity employer = QEmployerEntity.employerEntity;

        JPQLQuery<EmployerEntity> query = from(employer);

        query.where(employer.eno.gt(0));
        query.where(employer.edelete.isFalse());
        query.where(employer.eapproved.isFalse());

        this.getQuerydsl().applyPagination(pageable, query);

        return returnEmployerList(employer, query, pageRequestDTO);
    }
}
