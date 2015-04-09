package com.daybreak.masterdb.midrevenue;

public class ThreadPool extends Thread {

	private String dest;
	private String origin;

	public ThreadPool(String origin, String dest) {
		this.dest = dest;
		this.origin = origin;
	}

	@Override
	public void run() {
		RevenueDAO.update(this.origin, this.dest);
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}

}
