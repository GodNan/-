package com.css.bdpfnew.web;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ResultView {

	private Boolean success;
	private String msg;
	private String redirectUrl;
}
