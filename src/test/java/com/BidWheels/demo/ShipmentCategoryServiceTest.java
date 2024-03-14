package com.BidWheels.demo;

import com.BidWheels.demo.Model.ShipmentCategory;
import com.BidWheels.demo.Repositry.ShipmentCategoryRepository;
import com.BidWheels.demo.service.ShipmentCategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
class ShipmentCategoryServiceTest {

    @Mock
    private ShipmentCategoryRepository shipmentCategoryRepository;

    @InjectMocks
    private ShipmentCategoryService shipmentCategoryService;

    @Test
    void getAllCategories() {
        // Prepare data
        List<ShipmentCategory> categories = new ArrayList<>();
        categories.add(new ShipmentCategory());
        categories.add(new ShipmentCategory());

        // Mock repository method
        Mockito.when(shipmentCategoryRepository.findAll()).thenReturn(categories);

        // Call service method
        List<ShipmentCategory> result = shipmentCategoryService.getAllCategories();

        // Verify
        assertEquals(categories.size(), result.size());
    }

    @Test
    void getCategoryById_CategoryFound() {
        // Prepare data
        ShipmentCategory category = new ShipmentCategory();
        category.setCategoryId(1L);

        // Mock repository method
        Mockito.when(shipmentCategoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Call service method
        ShipmentCategory result = shipmentCategoryService.getCategoryById(1L);

        // Verify
        assertEquals(category, result);
    }

    @Test
    void getCategoryById_CategoryNotFound() {
        // Mock repository method
        Mockito.when(shipmentCategoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        ShipmentCategory result = shipmentCategoryService.getCategoryById(1L);

        // Verify
        assertNull(result);
    }

    @Test
    void createCategory() {
        // Prepare data
        ShipmentCategory category = new ShipmentCategory();

        // Mock repository method
        Mockito.when(shipmentCategoryRepository.save(any(ShipmentCategory.class))).thenReturn(category);

        // Call service method
        ShipmentCategory result = shipmentCategoryService.createCategory(category);

        // Verify
        assertEquals(category, result);
    }

    @Test
    void updateCategory_CategoryFound() {
        // Prepare data
        ShipmentCategory category = new ShipmentCategory();
        category.setCategoryId(1L);
        category.setCategoryName("Old Name");

        ShipmentCategory updatedCategory = new ShipmentCategory();
        updatedCategory.setCategoryId(1L);
        updatedCategory.setCategoryName("New Name");

        // Mock repository method
        Mockito.when(shipmentCategoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(shipmentCategoryRepository.save(any(ShipmentCategory.class))).thenReturn(updatedCategory);

        // Call service method
        ShipmentCategory result = shipmentCategoryService.updateCategory(1L, updatedCategory);

        // Verify
        assertEquals(updatedCategory.getCategoryName(), result.getCategoryName());
    }

    @Test
    void updateCategory_CategoryNotFound() {
        // Prepare data
        ShipmentCategory updatedCategory = new ShipmentCategory();

        // Mock repository method
        Mockito.when(shipmentCategoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        ShipmentCategory result = shipmentCategoryService.updateCategory(1L, updatedCategory);

        // Verify
        assertNull(result);
    }
}
