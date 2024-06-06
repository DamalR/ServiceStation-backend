package lk.damal.hdrservice.controller;

import lk.damal.hdrservice.dto.CategoryDTO;
import lk.damal.hdrservice.dto.LubricantOilDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.RoleDTO;
import lk.damal.hdrservice.service.CategoryService;
import lk.damal.hdrservice.service.LubricantOilService;
import lk.damal.hdrservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private LubricantOilService lubricantOilService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/role/new")
    public ResponseDTO newRole(@RequestBody RoleDTO roleDTO) {
        return roleService.newRole(roleDTO);
    }

    @PostMapping("/oil/new")
    public ResponseDTO newLubricantOil(@RequestBody LubricantOilDTO lubricantOilDTO) {
        return lubricantOilService.newLubricantOil(lubricantOilDTO);
    }

    @PostMapping("/category/new")
    public ResponseDTO newCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.newCategory(categoryDTO);
    }
}
