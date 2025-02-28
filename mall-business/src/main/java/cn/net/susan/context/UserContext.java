package cn.net.susan.context;

import cn.net.susan.entity.sys.UserEntity;
import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 用户上下文
 *
 * @author 苏三，该项目是知识星球：java突击队 的内部项目
 * @date 2024/1/9 下午4:32
 */
public class UserContext {

    private static final TransmittableThreadLocal<UserEntity> THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    public static UserEntity getCurrentUser() {
        return THREAD_LOCAL.get();
    }

    /**
     * 设置当前用户信息
     *
     * @param userEntity 当前用户信息
     */
    public static void setCurrentUser(UserEntity userEntity) {
        THREAD_LOCAL.set(userEntity);
    }

    /**
     * 清空当前用户信息
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
