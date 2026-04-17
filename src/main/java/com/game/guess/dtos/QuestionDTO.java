package com.game.guess.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String answerText;
    private String audioBase64;      // ← Đổi sang Base64
    private String audioEn;
}
