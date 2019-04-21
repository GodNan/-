package com.css.bdpfnew.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:52:35
 * @Description:
 */
@Accessors
@Data
public class VoIdt {

	private VoCitizen voCitizen;
	private VoIdtResult voIdtResult;
	private VoEye voEye;
	private VoEar voEar;
	private VoSpeech voSpeech;
	private VoBody voBody;
	private VoIq voIq;
	private VoMental voMental;

}
