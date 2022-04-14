package nju.se.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.se.vo.AdviceForm;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author jh
 * @date 2022/4/12 17:06
 */
@TableName("advice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advice {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("content")
    private String content;

    @TableField("push_time")
    private LocalDateTime pushTime;

    public Advice(AdviceForm adviceForm) {
        this.content = adviceForm.getAdContent();
        pushTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advice that = (Advice) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
