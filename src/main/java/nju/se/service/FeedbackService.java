package nju.se.service;

import nju.se.po.Advice;
import nju.se.vo.AdviceForm;

/**
 * @author jh
 * @date 2022/4/12 17:04
 */
public interface FeedbackService {

    Advice pushAdvice(AdviceForm adviceForm);

}
