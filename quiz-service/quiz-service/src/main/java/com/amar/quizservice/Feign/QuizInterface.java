package com.amar.quizservice.Feign;

import com.amar.quizservice.Model.QuizWrapper;
import com.amar.quizservice.Model.SubmitResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            @RequestParam String category, @RequestParam Integer numQuiz);

    //get the questions by questionIds
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuizWrapper>> getQuestionsById(
            @RequestBody List<Integer> ids
    );

    //get the score for a submit
    @PostMapping("question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<SubmitResponse> responses);
}
