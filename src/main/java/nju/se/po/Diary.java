package nju.se.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.se.my_enum.DiaryPrivacyType;
import nju.se.vo.DiaryWriteForm;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author jh
 * @date 2022/4/12 17:06
 */
@TableName("diary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diary implements Serializable {

    /**
     * 日记id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 隐私性
     */
    @TableField(value = "privacy")
    private String privacy;

    /**
     * 日记标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 创建时间
     */
    @TableField(value = "write_date")
    private LocalDate writeDate;

    /**
     * 最后修改时间
     */
    @TableField(value = "modify_date")
    private LocalDateTime modifyDate;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Diary other = (Diary) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPrivacy() == null ? other.getPrivacy() == null : this.getPrivacy().equals(other.getPrivacy()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getWriteDate() == null ? other.getWriteDate() == null : this.getWriteDate().equals(other.getWriteDate()))
            && (this.getModifyDate() == null ? other.getModifyDate() == null : this.getModifyDate().equals(other.getModifyDate()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userId, this.privacy);
    }

}
