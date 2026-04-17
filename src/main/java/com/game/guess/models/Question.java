package com.game.guess.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "answer_en")
    private String answerEn;

    @Column(name = "audio_en")
    private String audioEn;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "answer_vi")
    private String answerVi;

    @Column(name = "audio_vi")
    private String audioVi;
}
