package nju.se.controller;

import nju.se.service.UserService;
import nju.se.vo.Response;
import nju.se.vo.UserForm;
import nju.se.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author jh
 * @date 2022/2/6 15:22
 */
@RestController
@CrossOrigin
@RequestMapping("user")
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
     * @param userForm 用户表单
     * @return 用户个人信息
     */
    @PostMapping("sign_in")
    public Response signIn(@RequestBody UserForm userForm) {
        UserVO user = userService.signIn(userForm);
        return Response.buildSuccess(user);
    }

    /**
     * 用户登录/注册界面 用户注册
     *
     * @param userForm 用户表单
     * @return 无
     */
    @PostMapping("/sign_up")
    public Response signUp(@RequestBody UserForm userForm) {
        UserVO user = userService.register(userForm);
        return Response.buildSuccess(user);
    }


    /**
     * 输入用户名时实时检测格式及用户存不存在
     *
     * @param username 用户名
     * @return 无
     */
    @GetMapping("/check_exist")
    @Valid
    public Response checkExist(@RequestParam(name = "username") String username) {
        Boolean exist = userService.checkExist(username);
        return Response.buildSuccess(exist);
    }

}
