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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "question_audio_url", nullable = false)
    private String questionAudioUrl;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "icon_url", nullable = false)
    private String iconUrl;

    @Column(name = "is_active", nullable = false)
    private String isActive;
}
