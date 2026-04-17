package com.game.guess.responses;

import com.game.guess.dtos.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NextQuestionResponse {
    private Long categoryId;
    private String questionText;
    private String questionAudioBase64;      // ← Base64
    private String questionAudio;
    private String imageBase64;              // ← Base64 (chỉ 1 ảnh)

    private List<QuestionDTO> options;
    private Long correctQuestionId;

}
