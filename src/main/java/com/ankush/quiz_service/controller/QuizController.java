package com.ankush.quiz_service.controller;

import com.ankush.quiz_service.model.Response;
import com.ankush.quiz_service.service.QuizService;
import com.ankush.quiz_service.wrapper.QuizDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ankush.quiz_service.wrapper.QuestionWrapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) //to request url parameters
    {
        try {
            return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNumQ(),quizDTO.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        try {
            return quizService.getQuizQuestions(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable Integer id, @RequestBody List<Response> responses) {
        try {
            return quizService.calculateResult(id, responses);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
