package com.game.guess.services.category;

import com.game.guess.models.Category;
import com.game.guess.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    private final String IMAGE_DIR = "uploads/categories/";

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateImageByName(String name, MultipartFile image) {

        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Image is required");
        }

        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // ===== Upload file mới =====
        String newImage = saveFile(image);

        // ===== Xóa file cũ =====
        deleteFile(category.getIcon());

        // ===== Update DB =====
        category.setIcon(newImage);

        return categoryRepository.save(category);
    }

    // ===== SAVE FILE =====
    private String saveFile(MultipartFile file) {
        try {
            // validate image
            if (!file.getContentType().startsWith("image/")) {
                throw new RuntimeException("Invalid file type");
            }

            String newName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path path = Paths.get(IMAGE_DIR + newName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            return newName;

        } catch (Exception e) {
            throw new RuntimeException("Upload failed");
        }
    }

    // ===== DELETE FILE =====
    private void deleteFile(String fileName) {
        try {
            if (fileName == null || fileName.isEmpty()) return;

            Path path = Paths.get(IMAGE_DIR + fileName);
            Files.deleteIfExists(path);

        } catch (Exception e) {
            // chỉ log, không throw để tránh fail API
            System.err.println("Cannot delete file: " + fileName);
        }
    }
}


