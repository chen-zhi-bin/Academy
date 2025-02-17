package com.academy.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("wnxt_user")
@Schema(title = "User对象", description = "会员表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "会员id")
    private String id;

    @Schema(title = "第三方授权ID")
    private String openid;

    @Schema(title = "手机号")
    private String mobile;

    @Schema(title = "用户邮箱")
    private String email;

    @Schema(title = "用户账号")
    private String username;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "昵称")
    private String nickname;

    @Schema(title = "性别 1 女，2 男")
    private Byte sex;

    @Schema(title = "年龄")
    private Byte age;

    @Schema(title = "用户头像")
    private String avatar;

    @Schema(title = "用户类型（0:系统用户 1:平台用户）")
    private Byte userType;

    @Schema(title = "用户签名")
    private String sign;

    @Schema(title = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Byte isDisabled;

    @TableLogic
    @Schema(title = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Byte isDeleted;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
