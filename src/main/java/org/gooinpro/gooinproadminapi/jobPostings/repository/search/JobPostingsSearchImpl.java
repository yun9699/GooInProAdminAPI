package org.gooinpro.gooinproadminapi.jobPostings.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.domain.QEmployerEntity;
import org.gooinpro.gooinproadminapi.jobPostings.domain.JobPostingsEntity;
import org.gooinpro.gooinproadminapi.jobPostings.domain.QJobPostingsEntity;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class JobPostingsSearchImpl extends QuerydslRepositorySupport implements JobPostingsSearch{

    public JobPostingsSearchImpl() {

        super(JobPostingsEntity.class);
    }

    @Override
    public PageResponseDTO<JobPostingsListDTO> jobPostingsList(Long eno, String jpname, PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("jpno").descending());

        QJobPostingsEntity jobPostings = QJobPostingsEntity.jobPostingsEntity;
        QEmployerEntity employer = QEmployerEntity.employerEntity;

        JPQLQuery<JobPostingsEntity> query = from(jobPostings);
        query.leftJoin(employer).on(jobPostings.employer.eq(employer));

        //eno != 0 일때만 고용인 별 리스트 출력
        if(eno != 0) query.where(employer.eno.eq(eno));

        //jpname != null 일때만 검색
        if(jpname != null) query.where(jobPostings.jpname.containsIgnoreCase(jpname));

        query.where(jobPostings.jpno.gt(0));
        query.where(jobPostings.jpdelete.isFalse());
        query.orderBy(jobPostings.jpno.desc());

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<JobPostingsListDTO> tupleQuery = query.select(
                Projections.bean(JobPostingsListDTO.class,
                        jobPostings.jpno,
                        employer.ename,
                        jobPostings.jpname,
                        jobPostings.jpregdate,
                        jobPostings.jphourlyRate
                )
        );

        List<JobPostingsListDTO> dtoList = tupleQuery.fetch();
        long total = tupleQuery.fetchCount();

        return PageResponseDTO.<JobPostingsListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
