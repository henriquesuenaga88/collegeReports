package com.matera.challenge.college.commons.utils;

public class DocumentUtils {

	public static boolean isEquals(String document1, String document2) {
		if (document1 == null || document2 == null) {
			return false;
		}
		document1 = document1.replaceAll("\\D+", "");
		document2 = document2.replaceAll("\\D+", "");
		
		return document1.equals(document2);
	}
	
}
