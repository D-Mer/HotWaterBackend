package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jh
 * @date 2022/4/13 3:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePwdForm {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String userPwd;

}
