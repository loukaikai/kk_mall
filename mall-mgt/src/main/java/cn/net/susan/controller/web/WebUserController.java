package cn.net.susan.controller.web;

import cn.net.susan.entity.auth.AuthUserEntity;
import cn.net.susan.entity.sys.UserEntity;
import cn.net.susan.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 给前端用的用户接口
 *
 * @author 苏三，该项目是知识星球：java突击队 的内部项目
 * @date 2024/1/9 下午4:58
 */
@Api(tags = "web用户操作", description = "web用户接口")
@RestController
@RequestMapping("/v1/web/user")
@Validated
public class WebUserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param authUserEntity 用户实体
     * @return 影响行数
     */
    @ApiOperation(notes = "用户登录", value = "用户登录")
    @PostMapping("/login")
    public void login(@Valid @RequestBody AuthUserEntity authUserEntity, HttpServletRequest request) {
        userService.login(authUserEntity, request);
    }

    /**
     * 用户退出登录
     *
     * @param userEntity 用户实体
     * @return 影响行数
     */
    @ApiOperation(notes = "用户退出登录", value = "用户退出登录")
    @PostMapping("/logout")
    public void logout(@RequestBody UserEntity userEntity) {
        userService.insert(userEntity);
    }
}
