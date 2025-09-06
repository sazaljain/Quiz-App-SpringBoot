package com.project.quizapp.controller;

import com.project.quizapp.model.Question;
import com.project.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestion")
    public List<Question> getAllQues(){
        return questionService.getAllQuestion();
        //return "Hi, Here are your questions";
    }
    @GetMapping("category/{category}")
    public List<Question> getQuesByCategory(@PathVariable String category) {
        return questionService.getQuesByCategory(category);
    }
    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
