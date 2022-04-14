package nju.se.regedit;


import nju.se.po.Advice;
import nju.se.service.DiaryService;
import nju.se.service.FeedbackService;
import nju.se.service.UserService;
import nju.se.vo.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author jh
 * @date 2021/2/17
 */
@Service("Regedit")
public class RegeditImpl implements Regedit {

    @Lazy
    @Resource(name = "User")
    private UserService userService;

    @Lazy
    @Resource(name = "Diary")
    private DiaryService diaryService;

    @Lazy
    @Resource(name = "Feedback")
    private FeedbackService feedbackService;


    //-----------------------------------------UserService--------------------------------------


    @Override
    public UserVO signIn(SignInForm signInForm) {
        return userService.signIn(signInForm);
    }

    @Override
    public UserVO register(SignUpForm signInForm) {
        return userService.register(signInForm);
    }

    @Override
    public Boolean checkExist(String email) {
        return userService.checkExist(email);
    }

    @Override
    public void changePwd(ChangePwdForm form) {
        userService.changePwd(form);
    }

    @Override
    public void updateUser(UpdateUserForm form) {
        userService.updateUser(form);
    }




    //-----------------------------------------DiaryService--------------------------------------
    @Override
    public Boolean writeDiary(DiaryWriteForm diaryWriteForm) {
        return diaryService.writeDiary(diaryWriteForm);
    }

    @Override
    public DiaryListVO selectDiaryPage(Integer userId, Integer pno, Integer pageSize) {
        return diaryService.selectDiaryPage(userId, pno, pageSize);
    }

    @Override
    public DiaryDetailVO selectDiary(Integer diaryId, Integer userId) {
        return diaryService.selectDiary(diaryId, userId);
    }

    @Override
    public Collection<DiaryInfoVO> selectDiaryByDateRange(Integer userId, LocalDate startDate, LocalDate endDate) {
        return diaryService.selectDiaryByDateRange(userId, startDate, endDate);
    }

    @Override
    public Boolean deleteDiary(DiaryDeleteForm form) {
        return diaryService.deleteDiary(form);
    }

    @Override
    public Collection<DiaryInfoVO> selectDiaryByTag(Integer userId, String tag) {
        return diaryService.selectDiaryByTag(userId, tag);
    }

    @Override
    public Boolean updateDiary(DiaryUpdateForm form) {
        return diaryService.updateDiary(form);
    }

    @Override
    public UserStatistic getUserStatistic(Integer userId) {
        return diaryService.getUserStatistic(userId);
    }

    @Override
    public Collection<String> getUserTags(Integer userId) {
        return diaryService.getUserTags(userId);
    }


    //-----------------------------------------FeedbackService--------------------------------------


    @Override
    public Advice pushAdvice(AdviceForm adviceForm) {
        return feedbackService.pushAdvice(adviceForm);
    }



}
