package com.game.guess.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private Long categoryId;

    private String answerText;

    private String audioBase64;      // ← Base64
}
