package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.CategoryDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.Category;
import lk.damal.hdrservice.model.LubricantOil;
import lk.damal.hdrservice.repository.CategoryRepository;
import lk.damal.hdrservice.repository.LubricantOilRepository;
import lk.damal.hdrservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LubricantOilRepository lubricantOilRepository;

    @Override
    public ResponseDTO newCategory(CategoryDTO categoryDTO) {
        String category = categoryDTO.getCategory();
        String vehicleType = categoryDTO.getVehicleType();
        double charges = categoryDTO.getCharges();
        Long oilId = categoryDTO.getOilId();

        if (category.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Category can not be null!"
            );
        } else if (vehicleType.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Vehicle type can not be null!"
            );
        } else if (charges <= 0) {
            return new ResponseDTO(
                    false,
                    "Charges can not be less than 0!"
            );
        } else {
            try {
                Category categoryData = categoryRepository.getCategoryByCategoryAndVehicleType(category, vehicleType);

                if (categoryData != null) {
                    return new ResponseDTO(
                            false,
                            "Category already exist!"
                    );
                } else {
                    Category categoryDetail = new Category();

                    categoryDetail.setCategory(category);
                    categoryDetail.setVehicleType(vehicleType);
                    categoryDetail.setCharges(charges);

                    if (oilId != null) {
                        categoryDetail.setLubricantOil(lubricantOilRepository.findById(oilId).get());
                    }

                    categoryRepository.save(categoryDetail);

                    CategoryDTO savedCategory = new CategoryDTO();

                    savedCategory.setCategoryId(categoryDetail.getCategoryId());
                    savedCategory.setCategory(categoryDetail.getCategory());
                    savedCategory.setVehicleType(categoryDetail.getVehicleType());
                    savedCategory.setCharges(categoryDetail.getCharges());

                    if (oilId != null) {
                        savedCategory.setOilId(categoryDetail.getLubricantOil().getOilId());
                    }

                    return new ResponseDTO(
                            true,
                            "Category saved successfully!",
                            savedCategory
                    );
                }
            } catch (Exception exception) {
                return new ResponseDTO(
                        false,
                        "Category can not add!",
                        exception
                );
            }
        }
    }
}
