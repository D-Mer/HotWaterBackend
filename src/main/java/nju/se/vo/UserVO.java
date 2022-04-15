package nju.se.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.se.my_enum.SexType;
import nju.se.po.User;
import nju.se.utils.DateUtil;


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
     * 用户token，目前直接使用id
     */
    private String token;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 个性签名
     */
    private String signs;

    /**
     * 性别，"0"为女性，"1"为男性
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 注册日期
     */
    private String regTime;

    /**
     * 头像地址
     */
    private String avatar;

    public UserVO(User user) {
        this.userId = user.getId();
        this.token = String.valueOf(this.userId);
        this.email = user.getEmail();
        this.userName = user.getUsername();
        this.signs = user.getSigns();
        this.sex = SexType.typeToVO(SexType.valueToType(user.getSex()));
        this.avatar = user.getAvatar();
        if (user.getBirthday() != null) {
            this.birthday = DateUtil.toStringDate(user.getBirthday());
        }
        this.regTime = DateUtil.toStringDate(user.getRegTime());
    }
}
