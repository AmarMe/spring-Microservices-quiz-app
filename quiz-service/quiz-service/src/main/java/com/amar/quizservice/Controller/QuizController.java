package com.amar.quizservice.Controller;

import com.amar.quizservice.Model.QuizDTO;
import com.amar.quizservice.Model.QuizWrapper;
import com.amar.quizservice.Model.SubmitResponse;
import com.amar.quizservice.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService){
        this.quizService=quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getTitle(),quizDTO.getNumQuiz(),quizDTO.getCategory());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuizWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getSubmitScore(@PathVariable Integer id,@RequestBody List<SubmitResponse> submitResponses){
        return quizService.getSubmitScore(id,submitResponses);
    }

}
