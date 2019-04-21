package com.css.bdpfnew.model.vo.Permission;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: GodNan
 * @Date: 2018/6/7 10:09
 * @Version: 1.0
 * @Description:
 */
@Data
public class VoSysPermissionToChild {

    private String uuid;

    private String menuCode;

    private String menuName;

    private String functionCode;

    private String functionName;

    private Long orderBy;

    private List<VoSysPermissionToChild> childList = new ArrayList<VoSysPermissionToChild>();

}
