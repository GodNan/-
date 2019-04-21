package com.css.bdpfnew.model.dto.report;

import lombok.Data;

/**
 * @Author lvmn
 * @Date 2018/11/28 16:02
 * @Description
 */
@Data
public class DtoCustomAge {
    /*
     * 区划id
     */
    private String cityId;

    private Integer[] age1;

    private Integer[] age2;

    private Integer[] age3;

    private Integer[] age4;

    private String[] certDate;

    private Integer hukouKind;

    private Integer gender;

    public Boolean getIsNull(){
        if (this.age1 != null && this.age1.length != 0){
            return false;
        }
        if (this.age2 != null && this.age2.length != 0){
            return false;
        }
        if (this.age3 != null && this.age3.length != 0){
            return false;
        }
        if (this.age4 != null && this.age4.length != 0){
            return false;
        }
        return true;
    }

    public void setDefaultAge(){
        this.age1[0] = 0;
        this.age1[1] = 5;
        this.age2[0] = 6;
        this.age2[1] = 15;
        this.age3[0] = 16;
        this.age3[1] = 59;
        this.age4[0] = 60;
    }
}
