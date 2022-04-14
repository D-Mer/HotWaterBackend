package nju.se.serviceImpl;

import nju.se.mapper.AdviceMapper;
import nju.se.po.Advice;
import nju.se.service.FeedbackService;
import nju.se.vo.AdviceForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2022/4/12 17:05
 */

@Service("Feedback")
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    AdviceMapper adviceMapper;

    @Override
    @Transactional
    public Advice pushAdvice(AdviceForm adviceForm) {
        Advice advice = new Advice(adviceForm);
        adviceMapper.insert(advice);
        return advice;
    }

}
