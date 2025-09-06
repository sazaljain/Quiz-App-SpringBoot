package com.project.quizapp.service;

import com.project.quizapp.dao.QuestionDao;
import com.project.quizapp.dao.QuizDao;
import com.project.quizapp.model.Question;
import com.project.quizapp.model.Quiz;
import com.project.quizapp.model.QuizWrapper;
import com.project.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category,int numQ,String title) {
        List<Question> questions = questionDao.findRandomQuesByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Successing", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuizWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz =quizDao.findById(id);
        List<Question> questionsFromDB =quiz.get().getQuestion();
        List<QuizWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB){
            QuizWrapper qw = new QuizWrapper(q.getId(),q.getQuestionType(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz =quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestion();
        int right=0;
        int i=0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
    return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
