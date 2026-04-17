package com.game.guess.controllers;

import com.game.guess.models.Category;
import com.game.guess.models.Question;
import com.game.guess.responses.NextQuestionResponse;
import com.game.guess.responses.ResponseObject;
import com.game.guess.services.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllQuestions( ) {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(ResponseObject.builder()
                .message("Get list of questions successfully")
                .status(HttpStatus.OK)
                .data(questions)
                .build());
    }
    @GetMapping("/byTopic")
    public ResponseEntity<ResponseObject> getQuestionByTopic(
            @RequestParam Long categoryId,
            @RequestParam(required = false) List<Long> excludedIds
    ) {
        NextQuestionResponse questions = questionService.getNextQuestion(categoryId, excludedIds);

        return ResponseEntity.ok(ResponseObject.builder()
                .message("Get questions successfully")
                .status(HttpStatus.OK)
                .data(questions)
                .build());
    }
    @PutMapping("/update-image")
    public ResponseEntity<?> updateImageById( @RequestParam Long id, @RequestParam MultipartFile image
    ) {
        Question question = questionService.updateImageById(id, image);
        return ResponseEntity.ok(question);
    }
}
