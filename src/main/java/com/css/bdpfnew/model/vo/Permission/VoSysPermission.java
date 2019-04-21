package com.css.bdpfnew.model.vo.Permission;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Accessors
@Data
public class VoSysPermission {

    private String uuid;

    private String menuCode;

    private String menuName;

    private String functionCode;

    private String functionName;

    private Long orderBy;

    private String parentUuid;

    private String parentPermissionMenuName;

    private List<Map<String,String>> parentPermission;
}
