package com.css.bdpfnew.model.dto;

import lombok.Data;

/**
 * @Author lvmn
 * @Date 2018/12/6 12:56
 * @Description
 */
@Data
public class CaLoginParamer {
    private String BJCA_SERVER_CERT;
    private String BJCA_TICKET;
    private String BJCA_TICKET_TYPE;
    private String departWEBAddress;
    private String departPhone;
}
