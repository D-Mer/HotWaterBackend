package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * @author jh
 * @date 2022/4/13 2:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryUpdateForm {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 日记id
     */
    private Integer diaryId;

    /**
     * 日记是否公开，"1"表示公开，"0"表示私密
     */
    private String privacy;

    /**
     * 日记标题
     */
    private String title;

    /**
     * 标签
     */
    private Collection<String> tags;

    /**
     * 富文本内容
     */
    private String content;

}
