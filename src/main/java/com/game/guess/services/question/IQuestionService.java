package com.game.guess.services.question;

import com.game.guess.models.Category;
import com.game.guess.models.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> getAllQuestions();
}
