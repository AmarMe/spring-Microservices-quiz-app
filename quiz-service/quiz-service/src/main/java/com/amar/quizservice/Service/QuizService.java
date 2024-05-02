package com.amar.quizservice.Service;


import com.amar.quizservice.Feign.QuizInterface;
import com.amar.quizservice.Model.Quiz;
import com.amar.quizservice.Model.QuizWrapper;
import com.amar.quizservice.Model.SubmitResponse;
import com.amar.quizservice.Repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizDao quizDao;
    private final QuizInterface quizInterface;
    @Autowired
    public QuizService(QuizDao quizDao,QuizInterface quizInterface){
        this.quizDao = quizDao;
        this.quizInterface=quizInterface;
    }


    public ResponseEntity<String> createQuiz(String title, int numQuiz, String category) {
        List<Integer> questionList= quizInterface.generateQuestionsForQuiz(category,numQuiz).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionList);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuizWrapper>> getQuizQuestions(Integer id) {

        List<Integer> questionIds=new ArrayList<>();
        Optional<Quiz> quiz=quizDao.findById(id);
        if(quiz.isPresent())
            questionIds=quiz.get().getQuestionIds();
        List<QuizWrapper> questionsForUser=quizInterface.getQuestionsById(questionIds).getBody();

        return new ResponseEntity<>( questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getSubmitScore(Integer id, List<SubmitResponse> submitResponses) {
        Integer score=quizInterface.getScore(submitResponses).getBody();
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
