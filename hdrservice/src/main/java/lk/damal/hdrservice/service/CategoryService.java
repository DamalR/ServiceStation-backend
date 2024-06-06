package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.CategoryDTO;
import lk.damal.hdrservice.dto.ResponseDTO;

public interface CategoryService {
    public ResponseDTO newCategory(CategoryDTO categoryDTO);
}
