package com.daybreak.masterdb.midrevenue;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static String removeDollar(String value) {
		value = StringUtils.trim(value);
		return StringUtils.remove(value, "$");
	}

}
