package org.gooinpro.gooinproadminapi.admin.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.admin.domain.QAdminEntity;
import org.gooinpro.gooinproadminapi.admin.dto.AdminListDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Log4j2
public class AdminSearchImpl extends QuerydslRepositorySupport implements AdminSearch {

    public AdminSearchImpl() {
        super(AdminEntity.class);
    }

    @Override
    public PageResponseDTO<AdminListDTO> searchAdmin(PageRequestDTO pageRequestDTO, String[] types, String keyword) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("admno").descending());

        QAdminEntity admin = QAdminEntity.adminEntity;
        JPQLQuery<AdminEntity> query = from(admin);

        // 기본 조건
        query.where(admin.admno.gt(0));
        query.where(admin.admdelete.isFalse());

        // 검색 조건
        if(types != null && keyword != null) {
            BooleanBuilder builder = new BooleanBuilder();
            for(String type: types) {
                switch(type) {
                    case "i":
                        builder.or(admin.admid.contains(keyword));
                        break;
                    case "n":
                        builder.or(admin.admname.contains(keyword));
                        break;
                    case "r":
                        builder.or(admin.admrole.contains(keyword));
                        break;
                }
            }
            query.where(builder);
        }

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<AdminListDTO> dtoQuery = query.select(
                Projections.bean(AdminListDTO.class,
                        admin.admno,
                        admin.admid,
                        admin.admname,
                        admin.admrole,
                        admin.admdelete,
                        admin.admregdate
                )
        );

        List<AdminListDTO> dtoList = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();

        return PageResponseDTO.<AdminListDTO>withAll()
                .dtoList(dtoList)
                .totalCount(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
