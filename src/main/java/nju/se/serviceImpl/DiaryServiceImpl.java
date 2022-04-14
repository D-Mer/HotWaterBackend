package nju.se.serviceImpl;

import nju.se.constant.ErrorMessage;
import nju.se.exception.DiaryException;
import nju.se.exception.UserException;
import nju.se.mapper.DiaryMapper;
import nju.se.mapper.UserMapper;
import nju.se.my_enum.DiaryPrivacyType;
import nju.se.po.Diary;
import nju.se.po.User;
import nju.se.regedit.Regedit;
import nju.se.service.DiaryService;
import nju.se.utils.DateUtil;
import nju.se.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author jh
 * @date 2022/4/12 17:05
 */
@Service("Diary")
public class DiaryServiceImpl implements DiaryService {

    @Resource(name = "Regedit")
    Regedit regedit;

    @Resource
    DiaryMapper diaryMapper;

    @Resource
    UserMapper userMapper;

    @Override
    @Transactional
    public Boolean writeDiary(DiaryWriteForm form) {
        Diary diary = diaryMapper.selectByUserIdAndDate(form.getUserId(), LocalDate.now());
        if (diary != null) {
            return false;
        }
        diary = new Diary();
        diary.setTitle(form.getTitle());
        diary.setContent(form.getContent());
        diary.setUserId(form.getUserId());
        diary.setPrivacy(DiaryPrivacyType.voToType(form.getPrivacy()).value);
        diary.setWriteDate(LocalDate.now());
        diary.setModifyDate(LocalDateTime.now());
        diaryMapper.insert(diary);
        if (form.getTags() != null && !form.getTags().isEmpty()) {
            diaryMapper.insertTags(diary.getId(), form.getTags());
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateDiary(DiaryUpdateForm form) {
        User user = userMapper.selectById(form.getUserId());
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        Diary diary = diaryMapper.selectById(form.getDiaryId());
        if (diary == null) {
            throw new DiaryException(ErrorMessage.DiaryError.DIARY_NOT_EXIST);
        }
        if (!diary.getUserId().equals(form.getUserId())) {
            throw new DiaryException(ErrorMessage.DiaryError.NO_SUCH_DIARY_OF_USER);
        }
        diary.setModifyDate(LocalDateTime.now());
        diary.setContent(form.getContent());
        diary.setPrivacy(DiaryPrivacyType.voToType(form.getPrivacy()).value);
        diary.setTitle(form.getTitle());
        diaryMapper.deleteDiaryTag(diary.getId());
        diaryMapper.updateById(diary);
        diaryMapper.insertTags(diary.getId(), form.getTags());
        return true;
    }

    @Override
    public DiaryListVO selectDiaryPage(Integer userId, Integer pno, Integer pageSize) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        Integer count = diaryMapper.selectDiaryCountByUserId(userId);
        Collection<DiaryInfoVO> diaries = diaryMapper.selectByUserIdPage(userId, pno * pageSize, pageSize);
        DiaryListVO res = new DiaryListVO();
        res.setCount(count);
        res.setDiaries(diaries);
        return res;
    }

    @Override
    public Collection<DiaryInfoVO> selectDiaryByDateRange(Integer userId, LocalDate startDate, LocalDate endDate) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        return diaryMapper.selectByDateRange(userId, startDate, endDate);
    }

    @Override
    public Collection<DiaryInfoVO> selectDiaryByTag(Integer userId, String tag) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        return diaryMapper.selectByTag(userId, tag);
    }

    @Override
    @Transactional
    public Boolean deleteDiary(DiaryDeleteForm form) {
        User user = userMapper.selectById(form.getUserId());
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        Diary diary = diaryMapper.selectById(form.getDiaryId());
        if (diary == null) {
            throw new DiaryException(ErrorMessage.DiaryError.DIARY_NOT_EXIST);
        }
        diaryMapper.deleteById(form.getDiaryId());
        return true;
    }

    @Override
    public DiaryDetailVO selectDiary(Integer diaryId, Integer userId) {
        Diary diary = diaryMapper.selectById(diaryId);
        if (diary == null) {
            throw new DiaryException(ErrorMessage.DiaryError.DIARY_NOT_EXIST);
        }
        DiaryDetailVO detail = new DiaryDetailVO();
        detail.setTitle(diary.getTitle());
        detail.setContent(diary.getContent());
        detail.setWriteDate(DateUtil.toStringDate(diary.getWriteDate()));
        detail.setTags(diaryMapper.getTagByDiaryId(diaryId));
        return detail;
    }


    @Override
    public UserStatistic getUserStatistic(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        UserStatistic statistic = diaryMapper.getUserDiaryStatisticByUserId(userId);
        statistic.setTags(diaryMapper.getAllTagsByUserId(userId));
        return statistic;
    }

    @Override
    public Collection<String> getUserTags(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        return diaryMapper.getAllTagsByUserId(userId);
    }

}
