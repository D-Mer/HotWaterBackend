package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jh
 * @date 2022/2/17 10:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

    /**
     * 用户名
     */
    private String email;

    /**
     * 密码
     */
    private String userPwd;

}
