package nju.se.controller;

import nju.se.vo.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jh
 * @date 2022/2/6 15:22
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @GetMapping("hello")
    public Response Hello() {
        return Response.buildSuccess("Hello");
    }

}
