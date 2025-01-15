package org.gooinpro.gooinproadminapi.admin.repository.search;

import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AdminSearchImpl extends QuerydslRepositorySupport implements AdminSearch {
    public AdminSearchImpl() {
        super(AdminEntity.class);
    }
}