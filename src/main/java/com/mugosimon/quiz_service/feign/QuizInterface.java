package com.mugosimon.quiz_service.feign;

import com.mugosimon.quiz_service.model.Response;
import com.mugosimon.quiz_service.utils.EntityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("api/v1/questions/generate")
    public EntityResponse<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);

    @PostMapping("api/v1/questions/fetchQuestions")
    public EntityResponse fetchQuestionsById(@RequestBody List<Integer> questionsID);

    @PostMapping("api/v1/questions/getScore")
    public EntityResponse fetchScore(@RequestBody List<Response> responses);

}
