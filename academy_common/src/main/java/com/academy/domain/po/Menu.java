package com.academy.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单权限表
 **/
@Data
@TableName("wnxt_menu")
@Schema(title = "Menu对象", description = "菜单权限表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "菜单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "菜单名称")
    private String menuName;

    @Schema(title = "父菜单ID")
    private Long parentId;

    @Schema(title = "显示顺序")
    private Integer orderNum;

    @Schema(title = "路由地址")
    private String path;

    @Schema(title = "组件路径")
    private String component;

    @Schema(title = "路由参数")
    private String query;

    @Schema(title = "是否为外链（0是 1否）")
    private Integer isFrame;

    @Schema(title = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    @Schema(title = "菜单状态（0显示 1隐藏）")
    private String visible;

    @Schema(title = "菜单状态（0正常 1停用）")
    private String status;

    @Schema(title = "权限标识")
    private String perms;

    @Schema(title = "菜单图标")
    private String icon;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 子菜单 */
    private List<Menu> children = new ArrayList<Menu>();
}
