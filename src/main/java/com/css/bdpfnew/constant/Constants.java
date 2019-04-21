package com.css.bdpfnew.constant;

import java.util.HashMap;

/**
 * 
 * @description: 通用常量类, 单个业务的常量请单开一个类, 方便常量的分类管理
 * @date: 2017/10/24 10:15
 */
public class Constants {

	public static final String USER_NAME = "username";
	public static final String PASS_WORD = "password";

	public static final String SUCCESS_CODE = "100";
	public static final String SUCCESS_MSG = "请求成功";

	/**
	 * session中存放用户信息的key值
	 */
	public static final String SESSION_USER_INFO = "userInfo";
	public static final String SESSION_ROLE_INFO = "roleInfo";
	public static final String SESSION_USER_PERMISSION = "userPermission";

	// 流程
	// 政务网市内迁移
	public static final int PROCESS_AREAMOVE = 8;
	// 互联网区内迁移
	private static final int PROCESS_AREAMOVE_HL = 89;

	// 互联网跨区迁入
	public static final int PROCESS_AREAMOVE_HL_IN = 892;

	// 流程节点值
	// 录入/申请
	public static final Integer NEW = 10;

	// 政务网核验 handle
	public static final Integer HANDLE_ZW = 11;
	// 政务网受理 firstReview
	public static final Integer FIRST_REVIEW_ZW = 20;

	// 互联网核验 handleHL
	public static final int HANDLE_HL = 119;
	// 互联网受理 firstReviewHL
	public static final int FIRST_REVIEW_HL = 209;
	// 互联网无医院填写评定表
	public static final int IDTINFO_INPUT = 219;

	// 审核
	public static final Integer POST = 24;
	// 审核
	public static final Integer SECOND_REVIEW = 25;
	// 批准
	public static final Integer FINAL_REVIEW = 30;
	// 不通过
	public static final Integer DENY = 99;

	// 不通过对应options_adopt的值
	public static final String OPTIONS_ADOPT_DENY = "none";
	
	// 不通过权限
	public static final String PERMISSIONID_DENY = "nonebdpfnew";
	
	// 不通过日志
	public static final String REQUEST_NOTE_DENY = "评残未通过,流程结束";

	public static final String PERMISSIONID_AFTER_IDT = "secondReview";

	public static final HashMap<Integer, Integer> CURRENT_STATE_AFTER_IDT = new HashMap<Integer, Integer>() {
		{
			put(1, 20);
			put(5, 20);
			put(18, 25);
			put(58, 25);
			put(91, 20);
		}
	};

	public static final HashMap<Integer, Integer> FORMER_STATE_AFTER_IDT = new HashMap<Integer, Integer>() {
		{
			put(1, 11);
			put(5, 11);
			put(18, 219);
			put(58, 219);
			put(91, 10);
		}
	};

	public static final HashMap<Integer, String> REQUESTNOTES_AFTER_IDT = new HashMap<Integer, String>() {
		{
			put(1, "核验完成进入受理");
			put(5, "核验完成进入受理");
			put(18, "填写评定表完成进入审核");
			put(58, "填写评定表完成进入审核");
			put(91, "跨省迁入录入完成进入受理");
		}
	};

//	public static final HashMap<Integer, String> PERMISSIONIDS_AFTER_IDT = new HashMap<Integer, String>() {
//		{
//			put(1, "intranetApply:firstReview");
//			put(5, "intranetKindLevelChange:firstReview");
//			put(18, "internetApplyWithoutHospital:post");
//			put(19, "internetApplyWithHospital:post");
//			put(58, "internetKindLevelChangeWithoutHospital:post");
//			put(59, "internetKindLevelChangeWithHospital:post");
//		}
//	};

//	public static final HashMap<Integer, String> PERMISSIONIDS_AFTER_POST = new HashMap<Integer, String>() {
//		{
//			put(1, "intranetApply:secondReview");
//			put(5, "intranetKindLevelChange:secondReview");
//			put(18, "internetApplyWithoutHospital:secondReview");
//			put(19, "internetApplyWithHospital:secondReview");
//			put(58, "internetKindLevelChangeWithoutHospital:secondReview");
//			put(59, "internetKindLevelChangeWithHospital:secondReview");
//			put(120, "intranetCardReApply:secondReview");
//			put(1209, "internetCardReApply:secondReview");
//		}
//	};
}
