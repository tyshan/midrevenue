package com.daybreak.masterdb.midrevenue;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class ParseUtil {

	private static final int K = 1000;
	private static final int M = 1000000;
	private static final int B = 1000000000;

	public static BigDecimal parse(String parsevalue) {

		BigDecimal result = null;

		String[] parsevalues = null;
		if (parsevalue.indexOf("-") > 0) {
			parsevalues = parsevalue.split("-");
		} else if (parsevalue.indexOf("to") > 0) {
			parsevalues = parsevalue.split("to");
		} else {
			System.out.println("not found");
			return BigDecimal.ZERO;
		}

		if (parsevalues.length != 2) {
			return BigDecimal.ZERO;
		}

		BigDecimal firstvalue = null;
		BigDecimal lastvalue = null;
		int firstunit = 1;
		int lastunit = 1;
		boolean found = false;

		String first = parsevalues[0].trim();
		if (first.indexOf("$") == 0) {
			found = true;
		}
		first = StringUtil.removeDollar(first);
		if (first.indexOf("k") > 0) {
			firstunit = K;
			first = first.substring(0, first.indexOf("k"));
			found = true;
		}
		if (first.indexOf("mil.") > 0 || first.indexOf("mil") > 0
				|| first.indexOf("mil") > 0 || first.indexOf("m") > 0) {
			firstunit = M;
			first = first.substring(0, first.indexOf("m"));
			found = true;
		}
		if (first.indexOf("bil.") > 0 || first.indexOf("bil") > 0
				|| first.indexOf("bi") > 0 || first.indexOf("b") > 0) {
			firstunit = B;
			first = first.substring(0, first.indexOf("b"));
			found = true;
		}

		first = StringUtils.remove(first, ",").trim();
		try {
			firstvalue = new BigDecimal(first);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}

		String last = parsevalues[1].trim();
		if (last.indexOf("$") == 0) {
			found = true;
		}
		last = StringUtil.removeDollar(last);
		if (last.indexOf("mil.") > 0 || last.indexOf("mil") > 0
				|| last.indexOf("mil") > 0 || last.indexOf("m") > 0) {
			lastunit = M;
			last = last.substring(0, last.indexOf("m"));
			found = true;
		}
		if (last.indexOf("bil.") > 0 || last.indexOf("bil") > 0
				|| last.indexOf("bi") > 0 || last.indexOf("b") > 0) {
			lastunit = B;
			last = last.substring(0, last.indexOf("b"));
			found = true;
		}

		if (!found) {
			return BigDecimal.ZERO;
		}

		last = StringUtils.remove(last, ",").trim();

		try {
			lastvalue = new BigDecimal(last);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}

		if (firstunit == 1 && firstvalue.intValue() < 1000) {
			firstunit = lastunit;
		}
		if (lastunit == 1 && lastvalue.intValue() < 1000) {
			lastunit = firstunit;

		}

		BigDecimal firstpart = new BigDecimal(firstunit).multiply(firstvalue);
		BigDecimal lastpart = new BigDecimal(lastunit).multiply(lastvalue);
		if (firstpart.compareTo(lastpart) > 0) {
			return BigDecimal.ZERO;
		}

		System.out.println("first unit ### " + firstunit + ",lastunit ### "
				+ lastunit);

		System.out.println("worked ### " + first + "," + last);

		result = firstpart.add(lastpart).divide(new BigDecimal(2));

		return result;

	}
}
