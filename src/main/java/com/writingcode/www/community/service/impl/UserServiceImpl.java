package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.auth.source.IDataStore;
import com.writingcode.www.community.auth.util.SecurityUtil;
import com.writingcode.www.community.dao.RoleMapper;
import com.writingcode.www.community.dao.UserRoleMapper;
import com.writingcode.www.community.entity.po.Role;
import com.writingcode.www.community.entity.po.User;
import com.writingcode.www.community.dao.UserMapper;
import com.writingcode.www.community.entity.vo.LoginVo;
import com.writingcode.www.community.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private SecurityUtil securityUtil;

    @Resource
    private IDataStore dataStore;

    @Override
    public LoginVo login(String userName, String password) {
        Assert.notNull(userName, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");

        User user = userMapper.selectUserByLogin(userName, password);
        Assert.notNull(user, "该用户不存在");

        Integer roleId = userRoleMapper.selectRoleIdByUserId(user.getId());
        Assert.notNull(roleId, "系统错误");

        Role role = roleMapper.selectById(roleId);
        Assert.notNull(role, "系统错误");

        String token = securityUtil.login(String.valueOf(user.getId()), null, null);

        return new LoginVo().setRoleId(roleId).setUserId(user.getId()).setToken(token);
    }

    @Override
    public void logout(Long userId) {
        Assert.notNull(userId, "用户id不能为空");
        dataStore.remove(String.valueOf(userId));
    }
}
