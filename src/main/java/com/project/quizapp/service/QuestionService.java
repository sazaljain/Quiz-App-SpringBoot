package com.project.quizapp.service;

import com.project.quizapp.model.Question;
import com.project.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestion() {
        return questionDao.findAll();
    }

    public List<Question> getQuesByCategory(String category) {
        return questionDao.findByCategory( category);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }
}
