package nju.se.controller;

import nju.se.service.UserService;
import nju.se.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author jh
 * @date 2022/2/6 15:22
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource(name = "Regedit")
    private UserService userService;

    @GetMapping("hello")
    public Response hello() {
        return Response.buildSuccess("Hello");
    }

    /**
     * 用户登录/注册界面 用户登录
     *
     * @param signInForm 用户表单
     * @return 用户个人信息
     */
    @PostMapping("login")
    public Response signIn(@RequestBody SignInForm signInForm) {
        UserVO user = userService.signIn(signInForm);
        return Response.buildSuccess(user);
    }

    /**
     * 用户登录/注册界面 用户注册
     *
     * @param signUpForm 用户表单
     * @return 无
     */
    @PostMapping("reg")
    public Response signUp(@RequestBody SignUpForm signUpForm) {
        UserVO user = userService.register(signUpForm);
        return Response.buildSuccess(user);
    }


    /**
     * 输入用户名时实时检测格式及邮箱是否已被注册
     *
     * @param email 邮箱
     * @return 邮箱已注册：code = 402；邮箱未注册：code = 200
     */
    @GetMapping("selectEmail")
    public Response checkExist(@RequestParam(name = "email") String email) {
        Boolean exist = userService.checkExist(email);
        if (exist) {
            return Response.buildFailure(402, "该邮箱已被注册");
        }
        return Response.buildSuccess();
    }


    /**
     * 修改密码
     * @param form 表单
     * @return 是否成功
     */
    @PostMapping("updatePwd")
    public Response updatePwd(@RequestBody ChangePwdForm form) {
        userService.changePwd(form);
        return Response.buildSuccess();
    }


    /**
     * 修改用户个人信息
     * @param form 表单
     * @return 是否成功
     */
    @PostMapping("updateUser")
    public Response updateUser(@RequestBody UpdateUserForm form) {
        userService.updateUser(form);
        return Response.buildSuccess();
    }



}
