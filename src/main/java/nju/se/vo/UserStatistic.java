package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author jh
 * @date 2022/4/13 1:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatistic {

    /**
     * 用户所有日记的字符总数
     */
    private Integer charCount;

    /**
     * 用户的日记数
     */
    private Integer diaryCount;

    /**
     * 用户写过的所有标签
     */
    private Collection<String> tags;

}
