package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jh
 * @date 2022/4/13 3:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别，"0"为女性，"1"为男性
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 个性签名
     */
    private String signs;

}
