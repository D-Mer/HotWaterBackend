package nju.se.service;

import nju.se.vo.*;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author jh
 * @date 2022/4/12 16:18
 */
public interface DiaryService {

    /**
     * 写日记
     * @param diaryWriteForm 新建的日记表单
     * @return 用户信息vo
     */
    Boolean writeDiary(DiaryWriteForm diaryWriteForm);

    /**
     * 分页查询日记
     * @param userId 用户id
     * @param pno 页码，从 0 开始
     * @param pageSize 每页日记数
     * @return 列表数据
     */
    DiaryListVO selectDiaryPage(Integer userId, Integer pno, Integer pageSize);

    /**
     * 查看日记内容
     * @param diaryId 日记id
     * @param userId 用户id
     * @return 日记内容
     */
    DiaryDetailVO selectDiary(Integer diaryId, Integer userId);

    /**
     * 根据日期范围搜索日记
     * @param userId 用户id
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 列表数据
     */
    Collection<DiaryInfoVO> selectDiaryByDateRange(Integer userId, LocalDate startDate, LocalDate endDate);

    /**
     * 删除日记
     * @param form 日记表单
     * @return 是否成功
     */
    Boolean deleteDiary(DiaryDeleteForm form);

    /**
     * 根据标签搜索日记
     * @param userId 用户id
     * @param tag 标签
     * @return 列表数据
     */
    Collection<DiaryInfoVO> selectDiaryByTag(Integer userId, String tag);

    /**
     * 修改日记
     *
     * @param form 更新表单
     * @return 是否成功
     */
    Boolean updateDiary(DiaryUpdateForm form);

    /**
     * 获取用户统计数据
     * @param userId 用户id
     * @return 字符数、日记数、注册时长等
     */
    UserStatistic getUserStatistic(Integer userId);


    /**
     * 获取用户写过的所有标签
     * @param userId 用户iod
     * @return 用户写过的所有标签
     */
    Collection<String> getUserTags(Integer userId);
}
