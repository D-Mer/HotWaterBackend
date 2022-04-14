package nju.se.controller;

import nju.se.my_enum.DiaryPrivacyType;
import nju.se.service.DiaryService;
import nju.se.utils.DateUtil;
import nju.se.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author jh
 * @date 2022/4/12 16:16
 */
@RestController
@RequestMapping("api/diary")
public class DiaryController {

    @Resource(name = "Regedit")
    private DiaryService diaryService;

    /**
     * 写日记
     *
     * @param form 日记表单
     * @return 是否成功
     */
    @PostMapping("writeDiary")
    public Response writeDiary(@RequestBody DiaryWriteForm form) {
        boolean success = diaryService.writeDiary(form);
        if (success) {
            return Response.buildSuccess();
        } else {
            return Response.buildFailure("今天已发布日记");
        }
    }

    /**
     * 修改日记
     *
     * @param form 更新表单
     * @return 是否成功
     */
    @PostMapping("updateDiary")
    public Response updateDiary(@RequestBody DiaryUpdateForm form) {
        boolean success = diaryService.updateDiary(form);
        if (success) {
            return Response.buildSuccess();
        } else {
            return Response.buildFailure("今天已发布日记");
        }
    }

    /**
     * 删除日记
     *
     * @param form 删除表单
     * @return 是否成功
     */
    @PostMapping("delDiary")
    public Response delDiary(@RequestBody DiaryDeleteForm form) {
        boolean success = diaryService.deleteDiary(form);
        if (success) {
            return Response.buildSuccess();
        } else {
            return Response.buildFailure("今天已发布日记");
        }
    }

    /**
     * 获取日记内容
     *
     * @param diaryId 日记id
     * @param userId  用户id
     * @return 日记内容
     */
    @GetMapping("diaryDetails")
    public Response getDiaryDetails(@RequestParam(name = "diaryId") Integer diaryId,
                                    @RequestParam Integer userId) {
        DiaryDetailVO detail = diaryService.selectDiary(diaryId, userId);
        return Response.buildSuccess(detail);
    }

    /**
     * 分页选择日记
     *
     * @param userId   用户id
     * @param pno      页码，从0开始
     * @param pageSize 页大小
     * @return 当前页日记概览的列表
     */
    @GetMapping("selectDiary")
    public Response selectDiary(@RequestParam Integer userId,
                                @RequestParam Integer pno,
                                @RequestParam Integer pageSize) {
        DiaryListVO diaries = diaryService.selectDiaryPage(userId, pno, pageSize);
        return Response.buildSuccess(diaries);
    }

    /**
     * 根据日期范围搜索日记
     * @param userId 用户id
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 列表数据
     */
    @GetMapping("dateDiary")
    public Response selectDiaryByDateRange(@RequestParam Integer userId,
                                           @RequestParam String startDate,
                                           @RequestParam String endDate) {
        LocalDate start = DateUtil.parseDate(startDate).toLocalDate();
        LocalDate end = DateUtil.parseDate(endDate).toLocalDate();
        Collection<DiaryInfoVO> diaries = diaryService.selectDiaryByDateRange(userId, start, end);
        return Response.buildSuccess(diaries);
    }

    /**
     * 根据标签搜索日记
     * @param userId 用户id
     * @param kwd 标签
     * @return 列表数据
     */
    @GetMapping("selectTagDiary")
    public Response selectDiaryByTag(@RequestParam Integer userId,
                                     @RequestParam String kwd) {
        Collection<DiaryInfoVO> diaries = diaryService.selectDiaryByTag(userId, kwd);
        for (DiaryInfoVO info : diaries) {
            info.setPrivacy(DiaryPrivacyType.typeToVO(DiaryPrivacyType.valueToType(info.getPrivacy())));
        }
        return Response.buildSuccess(diaries);
    }


    /**
     * 用户的统计数据
     *
     * @param userId 用户id
     * @return {@link UserStatistic}
     */
    @GetMapping("totalDinfo")
    public Response userStatistic(@RequestParam(name = "userId") Integer userId) {
        UserStatistic statistic = diaryService.getUserStatistic(userId);
        return Response.buildSuccess(statistic);
    }


    /**
     * 获取用户所有写过的标签
     *
     * @param userId 用户id
     * @return 标签列表
     */
    @GetMapping("diaryTags")
    public Response allTags(@RequestParam(name = "userId") Integer userId) {
        Collection<String> tags = diaryService.getUserTags(userId);
        return Response.buildSuccess(tags);
    }

}
