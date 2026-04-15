package com.game.guess.controllers;

import com.game.guess.models.Category;
import com.game.guess.responses.ResponseObject;
import com.game.guess.services.category.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
//@Validated
public class CategoryController {
    private final CategoryService categoryService;


    //Hiện tất cả các categories
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllCategories( ) {
        List<Category> categories = categoryService.getAllCategories();

        return ResponseEntity.ok(ResponseObject.builder()
                        .message("Get list of categories successfully")
                        .status(HttpStatus.OK)
                        .data(categories)
                .build());
    }
    @PutMapping("/update-image")
    public ResponseEntity<?> updateImageByName( @RequestParam String name, @RequestParam MultipartFile image
    ) {
        Category category = categoryService.updateImageByName(name, image);
        return ResponseEntity.ok(category);
    }

}

