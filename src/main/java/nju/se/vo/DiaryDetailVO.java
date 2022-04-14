package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * @author jh
 * @date 2022/4/12 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDetailVO {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private Collection<String> tags;

    /**
     * 上传日期
     */
    private String writeDate;

}
