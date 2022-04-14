package nju.se.service;


import nju.se.vo.*;

import java.util.Collection;

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


    void changePwd(ChangePwdForm form);

    void updateUser(UpdateUserForm form);
}
