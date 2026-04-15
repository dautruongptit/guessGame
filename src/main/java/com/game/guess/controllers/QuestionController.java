package com.game.guess.controllers;

import com.game.guess.models.Question;
import com.game.guess.responses.ResponseObject;
import com.game.guess.services.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quests")
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllCategories( ) {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(ResponseObject.builder()
                .message("Get list of questions successfully")
                .status(HttpStatus.OK)
                .data(questions)
                .build());
    }
}
