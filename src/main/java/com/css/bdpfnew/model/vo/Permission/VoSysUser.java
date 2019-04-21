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
public class VoSysUser {

    private String uuid;

    private String username;

    private String password;

    private String nickname;

    private String qxid;

    private String jdid;

    private String cityName;

    private Set<String> roleUuids = new HashSet<String>();
}
