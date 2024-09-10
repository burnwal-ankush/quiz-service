package com.ankush.quiz_service.feing;

import com.ankush.quiz_service.model.Response;
import com.ankush.quiz_service.wrapper.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping(path = "/question/generate")
    public ResponseEntity<List<Integer>> generateQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);

    @PostMapping(path = "/question/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);

    @PostMapping(path = "/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
