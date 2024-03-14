package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.ShipmentCategory;
import com.BidWheels.demo.service.ShipmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ShipmentCategoryController {

    @Autowired
    private ShipmentCategoryService shipmentCategoryService;

    @GetMapping("/getdata")
    public ResponseEntity<List<ShipmentCategory>> getAllCategories() {
        List<ShipmentCategory> categories = shipmentCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ShipmentCategory> getCategoryById(@PathVariable Long categoryId) {
        ShipmentCategory category = shipmentCategoryService.getCategoryById(categoryId);

        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ShipmentCategory> createCategory(@RequestBody ShipmentCategory category) {
        ShipmentCategory createdCategory = shipmentCategoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ShipmentCategory> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody ShipmentCategory updatedCategory
    ) {
        ShipmentCategory category = shipmentCategoryService.updateCategory(categoryId, updatedCategory);

        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
