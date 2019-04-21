/**
 * 
 */
package com.css.bdpfnew.util;

import com.css.bdpfnew.constant.Constants;

/**
 * @Author erDuo
 * @Date 2018年10月23日 上午9:35:37
 * @Description
 */
public class WorkFlowUtil {

	private static final int NEW_APPLY = 1;
	private static final int NEW_APPLY_HL_WITH_HOSPITAL = 19;
	private static final int NEW_APPLY_HL_WITHOUT_HOSPITAL = 18;
	public static final int KIND_LEVEL_CHANGE = 5;
	public static final int KIND_LEVEL_CHANGE_HL_WITH_HOSPITAL = 59;
	public static final int KIND_LEVEL_CHANGE_HL_WITHOUT_HOSPITAL = 58;

	public static final int ZHUXIAO = 4;
	private static final int ZHUXIAO_HL = 49;

	private static final int ZHUXIAO_RESUME = 46;

	public static final int AREAMOVE = 8;
	private static final int AREAMOVE_HL = 89; // 区内迁移
	private static final int AREAMOVE_HL_OUT = 891; // 跨区迁出
	private static final int AREAMOVE_HL_IN = 892; // 跨区迁入
	private static final int AREA_CHNAGE_IN = 91;
	private static final int AREA_CHNAGE_OUT_HL = 929;

	public static final int CARD_NEW_APPLY = 110;
	public static final int CARD_NEW_APPLY_HL = 1109;
	public static final int CARD_RE_APPLY = 120;
	public static final int CARD_RE_APPLY_HL = 1209;
	public static final int CARD_ALTER_APPLY = 140;
	public static final int CARD_ALTER_APPLY_HL = 1409;

	public static boolean isCardApplyHulian(Integer processId) {
		return processId == CARD_ALTER_APPLY_HL || processId == CARD_RE_APPLY_HL;
	}

	public static boolean isCardNewApply(Integer processId) {
		return processId == CARD_NEW_APPLY;
	}

	public static boolean isCardReApply(Integer processId) {
		return processId == CARD_RE_APPLY || processId == CARD_RE_APPLY_HL;
	}

	public static boolean isCardApply(Integer processId) {
		return processId == CARD_NEW_APPLY || processId == CARD_NEW_APPLY_HL || processId == CARD_RE_APPLY
				|| processId == CARD_RE_APPLY_HL || processId == CARD_ALTER_APPLY || processId == CARD_ALTER_APPLY_HL;
	}

	public static boolean isApply(Integer processId) {
		return processId == AREA_CHNAGE_IN || processId == NEW_APPLY || processId == KIND_LEVEL_CHANGE
				|| processId == NEW_APPLY_HL_WITH_HOSPITAL || processId == NEW_APPLY_HL_WITHOUT_HOSPITAL
				|| processId == KIND_LEVEL_CHANGE_HL_WITH_HOSPITAL
				|| processId == KIND_LEVEL_CHANGE_HL_WITHOUT_HOSPITAL;
	}

	public static boolean isNewApply(Integer processId) {
		return processId == AREA_CHNAGE_IN || processId == NEW_APPLY || processId == NEW_APPLY_HL_WITH_HOSPITAL
				|| processId == NEW_APPLY_HL_WITHOUT_HOSPITAL;
	}

	public static boolean isZhuxiao(Integer processId) {
		return processId == ZHUXIAO || processId == ZHUXIAO_HL;
	}

	public static boolean isZhuxiaoResume(Integer processId) {
		return processId == ZHUXIAO_RESUME;
	}

	public static boolean isAreamoveFinished(Integer processId) {
		return processId == AREAMOVE || processId == AREAMOVE_HL || processId == AREAMOVE_HL_IN;
	}

	public static boolean isAreamoveProcess(Integer processId) {
		return processId == AREAMOVE || processId == AREAMOVE_HL || processId == AREAMOVE_HL_OUT
				|| processId == AREAMOVE_HL_IN;
	}

	public static boolean isAreamoveHlOut(Integer processId) {
		return processId == AREAMOVE_HL_OUT;
	}

	public static boolean isAreaChange(Integer processId) {
		return processId == AREA_CHNAGE_OUT_HL;
	}

	public static boolean isHulian(Integer processId) {
		return processId == NEW_APPLY_HL_WITH_HOSPITAL || processId == KIND_LEVEL_CHANGE_HL_WITH_HOSPITAL
				|| processId == NEW_APPLY_HL_WITHOUT_HOSPITAL || processId == KIND_LEVEL_CHANGE_HL_WITHOUT_HOSPITAL;
	}

	public static boolean isNewApplyHulian(Integer processId) {
		return processId == NEW_APPLY_HL_WITHOUT_HOSPITAL || processId == NEW_APPLY_HL_WITH_HOSPITAL;
	}

	public static boolean isKindLevelChangeHulian(Integer processId) {
		return processId == KIND_LEVEL_CHANGE_HL_WITHOUT_HOSPITAL || processId == KIND_LEVEL_CHANGE_HL_WITH_HOSPITAL;
	}

	public static boolean isNewApplyWithoutHospital(Integer processId) {
		return processId == NEW_APPLY_HL_WITHOUT_HOSPITAL;
	}

	public static boolean isHulianApplyWithoutHospital(Integer processId) {
		return processId == NEW_APPLY_HL_WITHOUT_HOSPITAL || processId == KIND_LEVEL_CHANGE_HL_WITHOUT_HOSPITAL;
	}

	public static boolean isHulianKindLevelChangeWithHospital(Integer processId) {
		return processId == KIND_LEVEL_CHANGE_HL_WITH_HOSPITAL;
	}

	public static boolean isHeyanShouli(Integer handleState) {
		return handleState == Constants.HANDLE_HL || handleState == Constants.FIRST_REVIEW_HL;
	}

}
