package org.gooinpro.gooinproadminapi.customersupport.repository.faq;

import org.gooinpro.gooinproadminapi.customersupport.domain.FAQEntity;
import org.gooinpro.gooinproadminapi.customersupport.repository.faq.search.FAQSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQEntity, Long>, FAQSearch {
}
