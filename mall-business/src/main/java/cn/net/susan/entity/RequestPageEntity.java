package cn.net.susan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 分页请求实体
 *
 * @author 苏三，该项目是知识星球：java突击队 的内部项目
 * @date 2024/1/4 下午3:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestPageEntity implements Serializable {

    private static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 页码，默认从一页开始
     */
    private Integer pageNo = 1;

    /**
     * 每页大小，默认一页查询10条数据
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 获取分页开始位置
     *
     * @return 分页开始位置
     */
    public Integer getPageBegin() {
        if (Objects.isNull(this.pageNo)) {
            this.pageNo = 1;
        }

        if (Objects.isNull(this.pageSize)) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }

        return (this.pageNo - 1) * this.pageSize;
    }
}
