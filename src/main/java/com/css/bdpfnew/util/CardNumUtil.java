/**
 * 
 */
package com.css.bdpfnew.util;

/**
 * @Author erDuo
 * @Date 2018年11月28日 上午10:43:50
 * @Description
 */
public class CardNumUtil {

	public static void main(String[] args) {

		String cardNumOld = "21062419780901730413B3";
		String cardNum = "21062419780901730472";

		int n = 10;

		while (n > 0) {
			cardNumOld = getCardNumNewForReApplyCard(cardNumOld);
			System.out.println(cardNumOld);
			n--;
		}

//		System.out.println(getCardNumNew("21062419780901730413", "21062419780901730472"));
//
//		System.out.println(getCardNumNew("21062419780901730413B3", "21062419780901730472"));
//
//		System.out.println(getCardNumNewForReApplyCard("21062419780901730413B3"));
//
//		System.out.println(getCardNumNewForReApplyCard("21062419780901730413"));

	}

	public static String getCardNumNew(String cardNumOld, String cardNum) {

		if (cardNumOld != null && cardNumOld.indexOf("B") > -1) {
			String B = cardNumOld.substring(20, cardNumOld.length());
			return cardNum + B;
		} else {
			return cardNum;
		}

	}

	public static String getCardNumNewForReApplyCard(String cardNumOld) {
		if (cardNumOld != null && cardNumOld.indexOf("B") > -1) {
			String B = cardNumOld.substring(21, cardNumOld.length());
			Integer BNum = Integer.parseInt(B);
			return cardNumOld.substring(0, 20) + "B" + (BNum + 1);
		} else {
			return cardNumOld + "B1";
		}

	}

}
