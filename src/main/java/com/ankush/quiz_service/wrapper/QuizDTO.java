package com.ankush.quiz_service.wrapper;

public class QuizDTO {

    private String category;
    private Integer numQ;
    private String title;

    public QuizDTO() {
    }

    public QuizDTO(String category, Integer numQ, String title) {
        this.category = category;
        this.numQ = numQ;
        this.title = title;
    }

    public Integer getNumQ() {
        return numQ;
    }

    public void setNumQ(Integer numQ) {
        this.numQ = numQ;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
