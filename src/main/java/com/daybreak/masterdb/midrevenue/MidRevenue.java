package com.daybreak.masterdb.midrevenue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

public class MidRevenue {
	ExecutorService pool = Executors.newFixedThreadPool(20);

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
				pool.execute(new ThreadPool(s, df.format(result)));
				// RevenueDAO.update(s, df.format(result));
			}
		}

		pool.shutdown();
	}

}
