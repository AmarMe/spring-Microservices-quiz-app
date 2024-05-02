package com.amar.questionservice.Service;

import com.amar.questionservice.Model.Question;
import com.amar.questionservice.Model.QuizWrapper;
import com.amar.questionservice.Model.SubmitResponse;
import com.amar.questionservice.Repository.QuestionDAO;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionDAO questionDAO;
    @Autowired
    public QuestionService(QuestionDAO questionDAO){
        this.questionDAO=questionDAO;
    }


    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDAO.findAll(),HttpStatus.OK);
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> addQuestion(Question question) {
         questionDAO.save(question);
         return new ResponseEntity<>("added Successfully",HttpStatus.CREATED);
    }

    @Transactional
    public Question updateQuestionById(Integer id, Question question) {
        Question question1 =questionDAO.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Question ID"+id+" Not exitsts"));
        if(question.getDifficultyLevel()!=null || !question1.getDifficultyLevel().isEmpty()){
            if(question.getDifficultyLevel().equals(question1.getDifficultyLevel()))
                throw new IllegalStateException("Updated and Old values are same");
            question1.setDifficultyLevel(question.getDifficultyLevel());
        }else {
            throw new IllegalStateException("Category should not be null OR empty");
        }
        return question1;
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        boolean IdExists=questionDAO.existsById(id);
        if(!IdExists){
            throw new IllegalStateException("Id "+id+" not exists");
        }
        questionDAO.deleteById(id);
        return new ResponseEntity<>("Deleted "+id+"'th question successfully",HttpStatus.OK);
    }


    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, Integer numQuiz) {
        List<Integer> questionsIdList=questionDAO.findRandomQuestionsBycategory(category,numQuiz);
        return new ResponseEntity<>(questionsIdList,HttpStatus.OK);
    }

    public ResponseEntity<List<QuizWrapper>> getQuestionsById(List<Integer> ids) {
        List<QuizWrapper> questionsList=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for(Integer id:ids){
            questions.add(questionDAO.findById(id).get());
        }
        for(Question q:questions){
            QuizWrapper quizWrapper=new QuizWrapper();
            quizWrapper.setId(q.getId());
            quizWrapper.setQuestionTitle(q.getQuestionTitle());
            quizWrapper.setOption1(q.getOption1());
            quizWrapper.setOption2(q.getOption2());
            quizWrapper.setOption3(q.getOption3());
            quizWrapper.setOption4(q.getOption4());
            questionsList.add(quizWrapper);
        }

        return new ResponseEntity<>(questionsList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<SubmitResponse> responses) {
        int score=0;

        for(SubmitResponse response:responses){
            Question question=questionDAO.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                score++;
        }

        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
