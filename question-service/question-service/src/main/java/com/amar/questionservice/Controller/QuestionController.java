package com.amar.questionservice.Controller;

import com.amar.questionservice.Model.Question;
import com.amar.questionservice.Model.QuizWrapper;
import com.amar.questionservice.Model.SubmitResponse;
import com.amar.questionservice.Service.QuestionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    Environment environment;
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
         return questionService.addQuestion(question);
    }

    @PutMapping("/update-question/{id}")
    public Question updateQuestionById(@PathVariable Integer id,
                                       @RequestBody Question question){
        return questionService.updateQuestionById(id,question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id){
        return questionService.deleteQuestionById(id);
    }

    //generate questions (questionIds) for quiz
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            @RequestParam String category, @RequestParam Integer numQuiz
    ){
        return questionService.generateQuestionsForQuiz(category,numQuiz);
    }

    //get the questions by questionIds
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuizWrapper>> getQuestionsById(
            @RequestBody List<Integer> ids
    ){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsById(ids);
    }

    //get the score for a submit
    @PostMapping("/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<SubmitResponse> responses){
        return questionService.getScore(responses);
    }
}
