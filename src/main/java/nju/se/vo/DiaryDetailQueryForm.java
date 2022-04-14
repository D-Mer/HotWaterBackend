package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jh
 * @date 2022/4/12 20:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDetailQueryForm {

    /**
     * 日记id
     */
    private Integer diaryId;

    /**
     * 用户id
     */
    private Integer userId;

}
