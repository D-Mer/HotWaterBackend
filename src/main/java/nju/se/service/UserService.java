package nju.se.service;


import nju.se.vo.UserForm;
import nju.se.vo.UserVO;

/**
 * @author jh
 * @date 2021/2/17
 */
public interface UserService {

    /**
     * 用户登录
     * @author wph
     * @param userForm 用户账户密码表单
     * @return 用户信息vo
     */
    UserVO signIn(UserForm userForm);

    /**
     * 用户注册
     * @author wph
     * @param userForm 用户账户密码表单
     */
    UserVO register(UserForm userForm);

    /**
     * 判断用户名存在
     * @param username 用户名
     */
    Boolean checkExist(String username);
}
