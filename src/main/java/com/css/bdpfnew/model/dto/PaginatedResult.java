package com.css.bdpfnew.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**

 */
@Accessors(chain = true)
@Data
public class PaginatedResult implements Serializable {

    private static final long serialVersionUID = 6191745064790884707L;

    private int currentPage; // Current page number
    private int totalPage; // Number of total pages
    private Object data; // Paginated resources

}
