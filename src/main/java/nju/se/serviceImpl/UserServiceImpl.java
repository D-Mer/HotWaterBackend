package nju.se.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nju.se.constant.ErrorMessage;
import nju.se.exception.UserException;
import nju.se.mapper.UserMapper;
import nju.se.my_enum.UserType;
import nju.se.po.User;
import nju.se.regedit.Regedit;
import nju.se.service.UserService;
import nju.se.vo.UserForm;
import nju.se.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2021/2/17
 */
@Service("User")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource(name = "Regedit")
    private Regedit regedit;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserVO signIn(UserForm userForm) {
        User user;
        user = userMapper.findByUsername(userForm.getUsername());
        //检验用户密码是否正确
        if (user != null && user.getPassword().equals(userForm.getPassword())) {
            return new UserVO(user);
        } else {
            throw new UserException(ErrorMessage.UserError.NAME_PASSWD_ERROR);
        }
    }

    @Override
    public UserVO register(UserForm userForm) {
        //检查用户是否已注册
        User user = userMapper.findByUsername(userForm.getUsername());
        if (user != null) {
            throw new UserException(ErrorMessage.UserError.USER_EXISTED_ERROR);
        }
        user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setType(UserType.Normal.value);
        userMapper.insert(user);
        return new UserVO(user);
    }

    @Override
    public Boolean checkExist(String username) {
        User user = userMapper.findByUsername(username);
        return null != user;
    }
}
