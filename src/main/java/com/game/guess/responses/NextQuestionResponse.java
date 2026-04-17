package com.game.guess.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NextQuestionResponse {
    private Long categoryId;
    private String questionText;
    private String questionAudioBase64;      // ← Base64
    private String imageBase64;              // ← Base64 (chỉ 1 ảnh)

    private List<QuestionResponse> options;
    private Long correctQuestionId;

}
