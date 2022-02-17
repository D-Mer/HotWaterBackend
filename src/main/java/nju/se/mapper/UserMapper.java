package nju.se.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import nju.se.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author jh
 * @date 2020/2/22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户实体
     */
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
}
