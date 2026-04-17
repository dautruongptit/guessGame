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

    @Column(name = "audio_vi", nullable = true)
    private String audioVi;

    @Column(name = "description_vi", nullable = true)
    private String descriptionVi;

    @Column(name = "question_en", nullable = true)
    private String questionEn;

    @Column(name = "audio_en", nullable = true)
    private byte[] audioEn;

    @Column(name = "description_en", nullable = true)
    private String descriptionEn;
    @Lob
    @Column(name = "icon", nullable = true)
    private byte[] icon;

    @Column(name = "is_active", nullable = true)
    private Boolean isActive;
}
