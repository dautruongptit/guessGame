package com.game.guess.services.question;

import com.game.guess.models.Category;
import com.game.guess.models.Question;
import com.game.guess.repositories.CategoryRepository;
import com.game.guess.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements  IQuestionService{
    private final QuestionRepository questionRepository;
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

}
