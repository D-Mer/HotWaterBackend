package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jh
 * @date 2022/4/13 0:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryInfoVO {

    /**
     * 标题
     */
    private String diaryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private String writeDate;

    /**
     * 隐私性，"0"表示私密，"1"表示公开
     */
    private String privacy;
}
