package com.writingcode.www.community.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Data
@Accessors(chain = true)
@TableName("household_info")
public class HouseholdInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 身份证
     */
    private String idNumber;

    /**
     * 性别 0-男 1-女
     */
    private Integer sex;

    /**
     * 籍贯
     */
    private String ancestralHome;

    /**
     * 户口所在地
     */
    private String residenceAddress;
}
