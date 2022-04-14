package nju.se.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author jh
 * @date 2022/2/17 10:50
 */
@TableName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("email")
    private String email;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("signs")
    private String signs;

    @TableField("avatar")
    private String avatar;

    @TableField("sex")
    private String sex;

    @TableField("birthday")
    private LocalDateTime birthday;

    @TableField("reg_time")
    private LocalDateTime regTime;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        return id.equals(that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }

}
