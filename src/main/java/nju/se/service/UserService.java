package nju.se.service;


import nju.se.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author jh
 * @date 2021/2/17
 */
public interface UserService {

    /**
     * 用户登录
     * @param signInForm 用户账户密码表单
     * @return 用户信息vo
     */
    UserVO signIn(SignInForm signInForm);

    /**
     * 用户注册
     * @param signInForm 用户账户密码表单
     */
    UserVO register(SignUpForm signInForm);

    /**
     * 判断邮箱是否已被注册
     * @param email 邮箱
     */
    Boolean checkExist(String email);


    /**
     * 修改密码
     * @param form 修改密码表单
     */
    void changePwd(ChangePwdForm form);

    /**
     * 更新用户信息
     * @param form 用户信息表单
     */
    void updateUser(UpdateUserForm form);

    /**
     * 更新头像
     * @return 更新后的图像url地址
     */
    String updateAvatar(Integer userId, MultipartFile avatar);

    /**
     * 获取用户头像
     * @return 图像文件流
     */
    File getAvatar(Integer userId);
}
