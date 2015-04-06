package com.daybreak.masterdb.midrevenue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> readRevenues() {
		List<String> revenues = new ArrayList<String>();;
		try {
			InputStream in = FileUtil.class.getClassLoader()
					.getResourceAsStream("f17-1.csv");
			String s = "";
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			while ((s = reader.readLine()) != null) {
				revenues.add(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return revenues;
	}
}
