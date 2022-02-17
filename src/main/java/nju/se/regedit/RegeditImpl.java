package nju.se.regedit;


import nju.se.service.UserService;
import nju.se.vo.UserForm;
import nju.se.vo.UserVO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2021/2/17
 */
@Service("Regedit")
public class RegeditImpl implements Regedit {

    @Lazy
    @Resource(name = "User")
    private UserService userService;


    //-----------------------------------------UserService--------------------------------------


    @Override
    public UserVO signIn(UserForm userForm) {
        return userService.signIn(userForm);
    }

    @Override
    public UserVO register(UserForm userForm) {
        return userService.register(userForm);
    }

    @Override
    public Boolean checkExist(String username) {
        return userService.checkExist(username);
    }


    //-----------------------------------------PaperService--------------------------------------


}
