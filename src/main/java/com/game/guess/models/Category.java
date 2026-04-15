package com.game.guess.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "question_vi", nullable = false)
    private String questionVi;

    @Column(name = "audio_vi", nullable = false)
    private String audioVi;

    @Column(name = "description_vi", nullable = false)
    private String descriptionVi;

    @Column(name = "question_en", nullable = false)
    private String questionEn;

    @Column(name = "audio_en", nullable = false)
    private String audioEn;

    @Column(name = "description_en", nullable = false)
    private String descriptionEn;

    @Column(name = "icon", nullable = false)
    private byte[] icon;

    @Column(name = "is_active", nullable = false)
    private String isActive;
}
