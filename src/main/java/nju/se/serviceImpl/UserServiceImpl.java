package nju.se.serviceImpl;

import nju.se.constant.ErrorMessage;
import nju.se.exception.UserException;
import nju.se.mapper.UserMapper;
import nju.se.my_enum.SexType;
import nju.se.po.User;
import nju.se.service.UserService;
import nju.se.utils.DateUtil;
import nju.se.utils.UrlUtil;
import nju.se.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author jh
 * @date 2021/2/17
 */
@Service("User")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    static final Set<String> supportAvatarFormats = new HashSet<String>() {{
        add("jpg");
        add("jpeg");
        add("png");
    }};

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
        user.setSex(SexType.Man.value);
        user.setEmail(signInForm.getEmail());
        user.setUsername(signInForm.getEmail());
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

    private String generateAvatarSavePath(Integer userId, String postFix) {
        return System.getProperty("user.home") + "/userData/" + userId + "." + postFix;
    }

    private String generateAvatarUrl(Integer userId) {
//        在路径中增加一个随机数或时间，不然前端vue不会重新渲染
//        return UrlUtil.getUrl() + "api/user/avatar?userId=" + userId;
        return UrlUtil.getUrl() + "api/user/avatar?userId=" + userId + "&updateTime=" + LocalDateTime.now();
    }

    @Override
    public String updateAvatar(Integer userId, MultipartFile avatar) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        String filename = avatar.getOriginalFilename();
        if (filename == null) {
            throw new UserException(ErrorMessage.UserError.INVALID_AVATAR_POSTFIX);
        }
        for (String format : supportAvatarFormats) {
            try {
                Files.deleteIfExists(Paths.get(generateAvatarSavePath(userId, format)));
            } catch (IOException ignored) {
            }
        }
        String[] nameSplit = avatar.getOriginalFilename().split("\\.");
        String postfix = nameSplit[nameSplit.length - 1];
        if (!supportAvatarFormats.contains(postfix)) {
            throw new UserException(ErrorMessage.UserError.INVALID_AVATAR_POSTFIX);
        }
        String path = generateAvatarSavePath(userId, postfix);
        File f = new File(path);
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(avatar.getBytes());
        } catch (IOException e) {
            throw new UserException(ErrorMessage.UserError.INVALID_AVATAR_FILE);
        }
        String url = generateAvatarUrl(userId);
        user.setAvatar(url);
        userMapper.updateById(user);
        return url;
    }

    @Override
    public File getAvatar(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ErrorMessage.UserError.USER_NOT_EXISTED);
        }
        if (user.getAvatar() == null) {
            throw new UserException(ErrorMessage.UserError.AVATAR_NOT_EXIST);
        }
        String path = "";
        for (String format : supportAvatarFormats) {
            path = generateAvatarSavePath(userId, format);
            if (Files.exists(Paths.get(path))) {
                break;
            }
        }
        return new File(path);
    }


}
