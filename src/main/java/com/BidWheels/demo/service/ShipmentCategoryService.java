package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.ShipmentCategory;
import com.BidWheels.demo.Repositry.ShipmentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentCategoryService {

    @Autowired
    private ShipmentCategoryRepository shipmentCategoryRepository;

    public List<ShipmentCategory> getAllCategories() {
        return shipmentCategoryRepository.findAll();
    }

    public ShipmentCategory getCategoryById(Long categoryId) {
        return shipmentCategoryRepository.findById(categoryId).orElse(null);
    }

    public ShipmentCategory createCategory(ShipmentCategory category) {
        return shipmentCategoryRepository.save(category);
    }

    public ShipmentCategory updateCategory(Long categoryId, ShipmentCategory updatedCategory) {
        ShipmentCategory existingCategory = getCategoryById(categoryId);

        if (existingCategory != null) {
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            existingCategory.setDescription(updatedCategory.getDescription());

            return shipmentCategoryRepository.save(existingCategory);
        } else {
            return null; // or throw an exception
        }
    }
}
