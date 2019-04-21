package com.css.bdpfnew.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**

 */
@Accessors(chain = true)
@Data
public class Error implements Serializable {

    private static final long serialVersionUID = 7660756960387438399L;

    private int code; // Error code
    private String message; // Error message

}
