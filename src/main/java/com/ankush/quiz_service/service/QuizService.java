package com.ankush.quiz_service.service;

import com.ankush.quiz_service.dao.QuizDao;
import com.ankush.quiz_service.feing.QuizInterface;
import com.ankush.quiz_service.model.Quiz;
import com.ankush.quiz_service.model.Response;
import com.ankush.quiz_service.wrapper.QuestionWrapper;
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
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Integer> questions = quizInterface.generateQuestionForQuiz(category, numQ).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to create quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try {
            Quiz quizOptional = quizDao.findById(id).get();
            List<Integer> questionIds = quizOptional.getQuestions();
            ResponseEntity<List<QuestionWrapper>> questionsWrapper = quizInterface.getQuestionFromId(questionIds);
            return questionsWrapper;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            ResponseEntity<Integer> score = quizInterface.getScore(responses);
            return score;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
