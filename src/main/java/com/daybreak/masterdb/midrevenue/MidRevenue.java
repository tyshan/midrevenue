package com.daybreak.masterdb.midrevenue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class MidRevenue {
	public void parse() {
		List<String> revenues = FileUtil.readRevenues();
		for (String s : revenues) {
			String rev = StringUtils.trim(s).toLowerCase();
			System.out.println("for parsing ### " + rev);
			if (rev.indexOf("#") >= 0) {
				continue;
			}
			BigDecimal result = ParseUtil.parse(rev);
			if (result.equals(BigDecimal.ZERO)) {
				System.out.println("can't parse!");
				continue;
			} else {
				DecimalFormat df = new DecimalFormat("#000");
				// System.out.println(result);
				RevenueDAO.update(rev, df.format(result));
			}
		}
	}

}
