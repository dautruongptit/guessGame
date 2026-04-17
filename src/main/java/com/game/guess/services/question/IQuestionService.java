package com.game.guess.services.question;


import com.game.guess.models.Question;
import com.game.guess.responses.NextQuestionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    List<Question> getAllQuestions();

    Optional<Question> findById(Long id);

     NextQuestionResponse getNextQuestion(Long categoryId, List<Long> excludedIds);

    Question updateImageById(Long id, MultipartFile image);
}
