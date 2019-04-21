/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew.view;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "VW_CARDMAKE", schema = "bdpfnew2018")
@Data
public class VwCardMakeCitizens implements Serializable {

	private static final long serialVersionUID = -8220856535814368110L;
	@Id
	private String bdpfid;
	private String name;
	private String citizenId;
	//private String cityid;
	//private Date modifyDate;

}