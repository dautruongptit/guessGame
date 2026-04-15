package com.game.guess.services.category;

import com.game.guess.models.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category updateImageByName(String name, MultipartFile image);


}
