package cn.net.susan.service.sys;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import cn.net.susan.entity.auth.AuthUserEntity;
import cn.net.susan.entity.sys.UserRoleEntity;
import cn.net.susan.mapper.sys.UserRoleMapper;
import cn.net.susan.util.AssertUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.susan.mapper.sys.UserMapper;
import cn.net.susan.entity.sys.UserConditionEntity;
import cn.net.susan.entity.sys.UserEntity;
import cn.net.susan.entity.ResponsePageEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户 服务层
 *
 * @author 苏三 该项目是知识星球：java突击队 的内部项目
 * @date 2024-01-08 14:03:43
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    public UserEntity findById(Long id) {
        return userMapper.findById(id);
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param userConditionEntity 用户信息
     * @return 用户集合
     */
    public ResponsePageEntity<UserEntity> searchByPage(UserConditionEntity userConditionEntity) {
        int count = userMapper.searchCount(userConditionEntity);
        if (count == 0) {
            return ResponsePageEntity.buildEmpty(userConditionEntity);
        }
        List<UserEntity> dataList = userMapper.searchByCondition(userConditionEntity);
        return ResponsePageEntity.build(userConditionEntity, count, dataList);
    }

    /**
     * 用户登录
     *
     * @param authUserEntity
     */
    public void login(AuthUserEntity authUserEntity, HttpServletRequest request) {

    }

    /**
     * 新增用户
     *
     * @param userEntity 用户信息
     * @return 结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insert(UserEntity userEntity) {
        UserConditionEntity userConditionEntity = new UserConditionEntity();
        userConditionEntity.setUserName(userEntity.getUserName());
        AssertUtil.notEmpty(userMapper.searchByCondition(userConditionEntity), "用户名称已存在");

        userConditionEntity = new UserConditionEntity();
        userConditionEntity.setEmail(userEntity.getEmail());
        AssertUtil.notEmpty(userMapper.searchByCondition(userConditionEntity), "邮箱已存在");

        userMapper.insert(userEntity);

        List<UserRoleEntity> userRoleEntities = buildUserRoleEntityList(userEntity);
        if (CollectionUtils.isNotEmpty(userRoleEntities)) {
            userRoleMapper.batchInsert(userRoleEntities);
        }
    }

    private List<UserRoleEntity> buildUserRoleEntityList(UserEntity userEntity) {
        if (CollectionUtils.isNotEmpty(userEntity.getRoleList())) {
            return userEntity.getRoleList().stream().map(x -> {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setUserId(userEntity.getId());
                userRoleEntity.setRoleId(x.getId());
                return userRoleEntity;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 修改用户
     *
     * @param userEntity 用户信息
     * @return 结果
     */
    public int update(UserEntity userEntity) {
        return userMapper.update(userEntity);
    }

    /**
     * 删除用户对象
     *
     * @param id 系统ID
     * @return 结果
     */
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

}
