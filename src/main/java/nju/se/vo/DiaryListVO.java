package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.se.po.Diary;

import java.util.Collection;

/**
 * @author jh
 * @date 2022/4/13 0:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryListVO {

    /**
     * 该用户的总日记数
     */
    private Integer count;

    /**
     * 当前页的日记数
     */
    private Collection<DiaryInfoVO> diaries;
}
