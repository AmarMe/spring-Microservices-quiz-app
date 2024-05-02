package com.amar.questionservice.Repository;

import com.amar.questionservice.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "select q.id from question q where q.category=:category order by RANDOM() limit :numQuiz"
            ,nativeQuery = true)
    List<Integer> findRandomQuestionsBycategory(String category, int numQuiz);
}
