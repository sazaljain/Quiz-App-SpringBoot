package com.project.quizapp.model;

import lombok.Data;

@Data
public class QuizWrapper {
    private Integer id;
    private String questionType;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuizWrapper(Integer id, String questionType, String option1, String option2, String option4, String option3) {
        this.id = id;
        this.questionType = questionType;
        this.option1 = option1;
        this.option2 = option2;
        this.option4 = option4;
        this.option3 = option3;
    }

}
