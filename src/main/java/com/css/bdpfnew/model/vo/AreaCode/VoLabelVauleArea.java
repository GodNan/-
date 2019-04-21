package com.css.bdpfnew.model.vo.AreaCode;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: GodNan
 * @Date: 2018/7/6 09:18
 * @Version: 1.0
 * @Description:
 */
@Data
public class VoLabelVauleArea {
    private String value;
    private String label;
    private Set<VoLabelVauleArea> cities = new HashSet<VoLabelVauleArea>();
}
