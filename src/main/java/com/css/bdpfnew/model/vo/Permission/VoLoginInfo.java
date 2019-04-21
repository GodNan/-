package com.css.bdpfnew.model.vo.Permission;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @Auther: GodNan
 * @Date: 2018/6/13 15:36
 * @Version: 1.0
 * @Description:
 */
@Accessors
@Data
public class VoLoginInfo {
    private String nickname;
    private String uuid;
    private String cityid;
    private String cityName;
    private Set<String> roleList;
    private Set<String> menuList;
    private Set<String> permissionList;
}
