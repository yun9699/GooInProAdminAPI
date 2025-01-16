package org.gooinpro.gooinproadminapi.admin.repository.search;

import org.gooinpro.gooinproadminapi.admin.dto.AdminListDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;

public interface AdminSearch {
     // @param types 검색 유형 배열 ("i":아이디, "n":이름, "r":역할)
     // @param keyword 검색어
    PageResponseDTO<AdminListDTO> searchAdmin(PageRequestDTO pageRequestDTO, String[] types, String keyword);
}
