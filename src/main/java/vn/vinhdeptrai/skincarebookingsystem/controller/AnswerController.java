package vn.vinhdeptrai.skincarebookingsystem.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vn.vinhdeptrai.skincarebookingsystem.dto.request.AnswerRequest;
import vn.vinhdeptrai.skincarebookingsystem.dto.response.AnswerResponse;
import vn.vinhdeptrai.skincarebookingsystem.dto.response.ApiResponse;
import vn.vinhdeptrai.skincarebookingsystem.service.AnswerService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerController {
    AnswerService answerService;

    @GetMapping
    public ApiResponse<List<AnswerResponse>> getAll() {
        return ApiResponse.<List<AnswerResponse>>builder()
                .result(answerService.getAnswerList())
                .build();
    }

    @GetMapping("/{answerId}")
    public ApiResponse<AnswerResponse> getAnswerById(@PathVariable int answerId) {
        return ApiResponse.<AnswerResponse>builder()
                .result(answerService.getAnswer(answerId))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<AnswerResponse> create(@RequestBody AnswerRequest request) {
        return ApiResponse.<AnswerResponse>builder()
                .result(answerService.create(request))
                .build();
    }

    @PostMapping("/create/{questionID}")
    public ApiResponse<AnswerResponse> createForQuestion(@RequestBody AnswerRequest request, @PathVariable int questionID) {
        return ApiResponse.<AnswerResponse>builder()
                .result(answerService.createForQuestion(request, questionID))
                .build();
    }

    @PostMapping("/createListAnswers/{questionID}")
    public ApiResponse<List<AnswerResponse>> createListAnswers(@RequestBody List<AnswerRequest> request
            , @PathVariable int questionID) {
        return ApiResponse.<List<AnswerResponse>>builder()
                .result(answerService.createListAnswers(request, questionID))
                .build();
    }

    @PutMapping("/update/{answerId}")
    ApiResponse<AnswerResponse> update(@PathVariable int answerId, @RequestBody AnswerRequest request) {
        return ApiResponse.<AnswerResponse>builder()
                .result(answerService.update(answerId, request))
                .build();
    }

    @DeleteMapping("/delete/{answerId}")
    void deleteAnswer(@PathVariable int answerId) {
        this.answerService.delete(answerId);
    }
}
