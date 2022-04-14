package nju.se.serviceImpl;

import nju.se.constant.ErrorMessage;
import nju.se.exception.UserException;
import nju.se.mapper.UserMapper;
import nju.se.my_enum.SexType;
import nju.se.po.User;
import nju.se.service.UserService;
import nju.se.utils.DateUtil;
import nju.se.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author jh
 * @date 2021/2/17
 */
@Service("User")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserVO signIn(SignInForm signInForm) {
        User user;
        user = userMapper.selectByEmail(signInForm.getEmail());
        //检验用户密码是否正确
        if (user != null && user.getPassword().equals(signInForm.getUserPwd())) {
            return new UserVO(user);
        } else {
            throw new UserException(ErrorMessage.UserError.NAME_PASSWD_ERROR);
        }
    }

    @Override
    @Transactional
    public UserVO register(SignUpForm signInForm) {
        //检查用户是否已注册
        if (checkExist(signInForm.getEmail())) {
            throw new UserException(ErrorMessage.UserError.USER_EXISTED_ERROR);
        }
        User user = new User();
        user.setSex(SexType.Unknown.value);
        user.setEmail(signInForm.getEmail());
        user.setPassword(signInForm.getUserPwd());
        user.setRegTime(LocalDateTime.now());
        userMapper.insert(user);
        return new UserVO(user);
    }

    @Override
    public Boolean checkExist(String email) {
        User user = userMapper.selectByUsername(email);
        return null != user;
    }

    @Override
    @Transactional
    public void changePwd(ChangePwdForm form) {
        User user = userMapper.selectById(form.getUserId());
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        } else if (!user.getPassword().equals(form.getOldPassword())) {
            throw new UserException(402, ErrorMessage.UserError.OLD_PASSWD_ERROR);
        }
        user.setPassword(form.getUserPwd());
        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void updateUser(UpdateUserForm form) {
        User user = userMapper.selectById(form.getUserId());
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        user.setUsername(form.getUserName());
        user.setSex(SexType.voToType(form.getSex()).value);
        user.setBirthday(DateUtil.parseDate(form.getBirthday()));
        user.setSigns(form.getSigns());
        userMapper.updateById(user);
    }


}
