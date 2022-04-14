package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jh
 * @date 2022/4/13 2:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDeleteForm {

    /**
     * 日记id
     */
    private Integer diaryId;

    /**
     * 用户id
     */
    private Integer userId;
}
