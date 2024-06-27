package com.mugosimon.quiz_service.service;

import com.mugosimon.quiz_service.dao.QuizDao;
import com.mugosimon.quiz_service.feign.QuizInterface;
import com.mugosimon.quiz_service.model.QuestionWrapper;
import com.mugosimon.quiz_service.model.Quiz;
import com.mugosimon.quiz_service.model.Response;
import com.mugosimon.quiz_service.utils.EntityResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QuizService {

    private final String TAG = "QuizService: ";
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public EntityResponse createQuiz(String category, Integer numQuestions, String questionTitle) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            log.info(TAG + "==> creating a quiz <==");

            EntityResponse<List<Integer>> questionResponse = quizInterface.getQuestionForQuiz(category, numQuestions);
            List<Integer> questions = questionResponse.getEntity();
            Quiz quiz = new Quiz();
            quiz.setTitle(questionTitle);
            quiz.setQuestionIDList(questions);

            Quiz savedQuiz = quizDao.save(quiz);

            entityResponse.setEntity(savedQuiz);
            log.info(TAG + " Quiz created successfully");
            entityResponse.setMessage("Quiz created successfully");
            entityResponse.setStatusCode(200);
        } catch (FeignException fe) {
            log.error(TAG + " Feign exception: " + fe.getMessage(), fe);
            entityResponse.setMessage("Error while fetching questions from question service");
            entityResponse.setStatusCode(fe.status());
        } catch (Exception e) {
            log.error(TAG + " Exception: " + e.getMessage(), e);
            entityResponse.setMessage("Error creating quiz");
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return entityResponse;
    }

    public EntityResponse fetchQuizQuestions(Integer id){
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            log.info(TAG+"==> fetching quiz questions<==");
            Quiz quiz = quizDao.findById(id).get();
            List<Integer> questionIDs = quiz.getQuestionIDList();

            EntityResponse<List<QuestionWrapper>> questions =  quizInterface.fetchQuestionsById(questionIDs);
            entityResponse.setEntity(questions.getEntity());
            log.info(TAG+" Quiz fetched successfully");
            entityResponse.setMessage("Quiz fetched successfully");
            entityResponse.setStatusCode(200);
            return entityResponse;
        }catch (Exception e){

            return entityResponse;
        }
    }

    public EntityResponse calculateResult(Integer id, List<Response> responseList){
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            log.info(TAG+"==> calculating results / score <==");

            EntityResponse<Integer> score = quizInterface.fetchScore(responseList);
            entityResponse.setEntity(score.getEntity());
            log.info(TAG+" Score calculated successfully");
            entityResponse.setMessage("Score calculated successfully");
            entityResponse.setStatusCode(200);

            return entityResponse;
        }catch (Exception e){

            return entityResponse;
        }
    }
}
