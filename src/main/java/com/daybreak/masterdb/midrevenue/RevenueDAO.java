package com.daybreak.masterdb.midrevenue;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RevenueDAO {

	/**
	 * update f16 from origin value to the dested value and add a signal U for
	 * updating
	 * 
	 */

	public static void update(String origin, String dest) {
		final String sql = "update masterdb1 set f17=?,status=\'U\' where f17=? and status is null or status=\'\'";
		Connection connection = ConnectionUtil.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dest);
			pstmt.setString(2, origin);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
