package com.game.guess.repositories;

import com.game.guess.models.Category;
import com.game.guess.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findById(Long id);
    @Query(value = """
        SELECT * FROM questions 
        WHERE category_id = :categoryId 
          AND is_active = true 
          AND id NOT IN :excludedIds
        ORDER BY RAND() 
        LIMIT 4
        """, nativeQuery = true)
    List<Question> findRandom4ByCategoryExcludeIds(
            @Param("categoryId") Long categoryId,
            @Param("excludedIds") List<Long> excludedIds);
}
