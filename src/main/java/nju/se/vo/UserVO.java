package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.se.po.User;


/**
 * 用户信息VO
 *
 * @author jh
 * @date 2021/2/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 权限等级
     */
    private String type;

    public UserVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.type = user.getType();
    }
}
