package com.mugosimon.quiz_service.controller;

import com.mugosimon.quiz_service.model.QuizDTO;
import com.mugosimon.quiz_service.model.Response;
import com.mugosimon.quiz_service.service.QuizService;
import com.mugosimon.quiz_service.utils.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public EntityResponse createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategoryName(),quizDTO.getNumberQuestion(),quizDTO.getTitle());
    }

    @GetMapping("/fetchByID")
    public EntityResponse fetchQuizByID(@RequestParam Integer id){
        return quizService.fetchQuizQuestions(id);
    }

    @GetMapping("/submit")
    public EntityResponse sumitQuiz(@RequestParam Integer id, @RequestBody List<Response> responseList){
        return quizService.calculateResult(id, responseList);
    }
}
