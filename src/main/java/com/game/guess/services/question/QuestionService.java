package com.game.guess.services.question;

import com.game.guess.models.Category;
import com.game.guess.models.Question;
import com.game.guess.repositories.CategoryRepository;
import com.game.guess.repositories.QuestionRepository;
import com.game.guess.responses.NextQuestionResponse;
import com.game.guess.responses.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor

public class QuestionService implements  IQuestionService{
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final Random random = new Random();
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public NextQuestionResponse getNextQuestion(Long categoryId, List<Long> excludedIds) {
        // 1. Lấy thông tin chủ đề (câu hỏi chung + audio chung)
        Category category = categoryRepository.findByIdAndIsActiveTrue(categoryId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chủ đề"));
        // 2. Lấy 4 questions ngẫu nhiên (không load ảnh)
        List<Question> selectedQuestions = questionRepository
                .findRandom4ByCategoryExcludeIds(categoryId, excludedIds != null ? excludedIds : List.of());
        if (selectedQuestions.size() < 4) {
            // Nếu ít hơn 4 (do loại trừ quá nhiều), bỏ qua excludedIds hoặc thông báo
            selectedQuestions = questionRepository
                    .findRandom4ByCategoryExcludeIds(categoryId, List.of());
        }

        // 3. Chọn ngẫu nhiên 1 question làm đáp án đúng
        Question correctQuestion = selectedQuestions.get(random.nextInt(selectedQuestions.size()));

        // 4. Tạo list 4 options
        List<QuestionResponse> options = new ArrayList<>();
        for (Question q : selectedQuestions) {
            options.add(new QuestionResponse(
                    q.getId(),
                    q.getCategoryId(),
                    q.getAnswerEn(),
                    q.getAudioEn()
            ));
        }

        // Trộn thứ tự options để đáp án đúng không luôn ở vị trí cố định
        Collections.shuffle(options);

        // 5. Load RIÊNG 1 ảnh của đáp án đúng (tối ưu hiệu năng)
        Question answerQuestion = questionRepository
                .findById(correctQuestion.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ảnh cho question"));
        String imageBase64 = Base64.getEncoder().encodeToString(answerQuestion.getImage());
        // Lấy audio câu hỏi chung (nếu có)
        String questionAudioBase64 = category.getQuestionEn() != null
                ? Base64.getEncoder().encodeToString(category.getAudioEn())
                : null;
        return new NextQuestionResponse(
                category.getId(),
                category.getDescriptionEn(),
                questionAudioBase64,
                imageBase64,
                options,
                correctQuestion.getId()
        );
    }

    @Override
    public Question updateImageById(Long id, MultipartFile image) {
        // ===== 1. Validate =====
        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Image is required");
        }

        try {
            if (!image.getContentType().startsWith("image/")) {
                throw new RuntimeException("Invalid image type");
            }

            // ===== 2. Find category =====
            Question question = questionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Not found"));
            // ===== 3. Convert file → byte[] =====
            byte[] imageBytes = image.getBytes();

            // ===== 4. Update DB =====
            question.setImage(imageBytes);

            return questionRepository.save(question);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Update image failed");
        }
    }


}
