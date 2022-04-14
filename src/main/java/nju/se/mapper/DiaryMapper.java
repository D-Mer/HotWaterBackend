package nju.se.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nju.se.po.Diary;
import nju.se.vo.DiaryInfoVO;
import nju.se.vo.UserStatistic;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author jh
 * @date 2022/4/12 17:22
 */
@Mapper
public interface DiaryMapper extends BaseMapper<Diary> {

    /**
     * 查找用户指定日期的日记
     * @param userId 用户id
     * @param now 日期
     * @return 日记
     */
    @Select("select * from diary where user_id=#{userId} and DATE(write_date)=DATE(#{now})")
    Diary selectByUserIdAndDate(Integer userId, LocalDate now);

    /**
     * 统计用户写过的日记数
     * @param userId 用户id
     * @return 日记数
     */
    @Select("select COUNT(*) from diary where user_id=#{useId}")
    Integer selectDiaryCountByUserId(Integer userId);

    /**
     * 根据用户id分页查找日记
     * @param userId 用户id
     * @param offset 页码 * 页大小
     * @param pageSize 每页日记数
     * @return 日记概览的列表
     */
    @Select("select id as diaryId, title as title, write_date as writeDate, privacy from diary where user_id=#{userId} order by write_date limit #{offset}, #{pageSize}")
    @ResultType(DiaryInfoVO.class)
    Collection<DiaryInfoVO> selectByUserIdPage(Integer userId, Integer offset, Integer pageSize);

    /**
     * 根据时间范围搜索指定用户的日记
     * @param userId 用户id
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    @Select("select id as diaryId, title as title, write_date as writeDate, privacy from diary where user_id=#{userId} and DATE(#{startDate}) <= write_date and write_date <= DATE(#{endDate})")
    @ResultType(DiaryInfoVO.class)
    Collection<DiaryInfoVO> selectByDateRange(Integer userId, LocalDate startDate, LocalDate endDate);

    /**
     * 根据标签搜索指定用户的日记
     * @param userId 用户id
     * @param tag 标签
     * @return 日记概览的列表
     */
    @Select("select id as diaryId, title as title, write_date as writeDate, privacy from diary, diary_tag where user_id=#{userId} and diary_tag.tag=#{tag} and diary.id=diary_tag.d_id")
    Collection<DiaryInfoVO> selectByTag(Integer userId, String tag);

    /**
     * 插入日记的tag
     * @param tags 日记的tag
     */
    @Insert("<script>" +
            "insert into diary_tag(d_id, tag) " +
            "   values" +
            "   <foreach collection='tags' item='i' separator=','>" +
            "       (#{diaryId}, #{i})" +
            "   </foreach> " +
            "</script>")
    void insertTags(Integer diaryId, Collection<String> tags);

    /**
     * 更新日记时，要先删除原有的tag
     * @param diaryId 日记id
     */
    @Delete("delete from diary_tag where d_id=#{diaryId}")
    void deleteDiaryTag(Integer diaryId);


    /**
     * 统计用户的数据
     * @param userId 用户id
     * @return 用户统计数据
     */
    @Select("select sum(length(content)) as charCount, count(*) as diaryCount from diary where user_id=#{userId}")
    @ResultType(UserStatistic.class)
    UserStatistic getUserDiaryStatisticByUserId(Integer userId);


    /**
     * 获取用户所有写过的tag
     * @param userId 用户id
     * @return 用户写过的所有tags
     */
    @Select("select distinct tag from diary, diary_tag where diary.user_id=#{userId} and diary.id=diary_tag.d_id")
    Collection<String> getAllTagsByUserId(Integer userId);


    /**
     * 根据日记id获取日记的tag
     * @param diaryId 日记id
     * @return 日记的tag
     */
    @Select("select distinct tag from diary_tag where d_id=#{diaryId}")
    Collection<String> getTagByDiaryId(Integer diaryId);

}
