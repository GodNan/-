package com.css.bdpfnew.model.vo.Permission;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: GodNan
 * @Date: 2018/6/7 09:57
 * @Version: 1.0
 * @Description:
 */
@Data
public class VoSysRole {

    private String uuid;

    private String roleName;

    private String roleDescribe;

    private Set<String> permissions = new HashSet<String>();
}
