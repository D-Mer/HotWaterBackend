package nju.se.controller;

import nju.se.service.FeedbackService;
import nju.se.vo.AdviceForm;
import nju.se.vo.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2022/4/12 17:02
 */
@RestController
@RequestMapping("api/advice")
public class FeedbackController {

    @Resource(name = "Regedit")
    FeedbackService feedbackService;

    @PostMapping("pushAdvice")
    public Response pushAdvice(@RequestBody AdviceForm adviceForm) {
        feedbackService.pushAdvice(adviceForm);
        return Response.buildSuccess();
    }
}
