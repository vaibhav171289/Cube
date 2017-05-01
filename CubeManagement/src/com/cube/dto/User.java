package com.cube.dto;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userid;
	private String noun;
	private String ts;
	private double latlong[];
	private String verb;
	private int timespent;
	private Properties properties;

	public User() {
	}

	public User(int userid, String noun, String ts, String latlong, String verb, int timespent,
			Properties properties) {
		this.userid = userid;
		this.noun = noun;
		this.ts = ts;
		String[] a = latlong.split(",");
		double loc[] = new double[2];
		for (int i = 0; i < 2; i++) {
			loc[i] = Double.parseDouble(a[i].trim());
		}
		this.latlong = loc;
		this.verb = verb;
		this.timespent = timespent;
		this.properties = properties;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getNoun() {
		return noun;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public double[] getLatlong() {
		return latlong;
	}

	public void setLatlong(double[] latlong) {
		this.latlong = latlong;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public int getTimespent() {
		return timespent;
	}

	public void setTimespent(int timespent) {
		this.timespent = timespent;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getnoun() {
		return noun;
	}

	public void setnoun(String noun) {
		this.noun = noun;
	}
	@Override
	public String toString()
	{
	return "user [userid=" + userid + ", noun=" + noun + ", ts=" + ts
	+ ", latlong=" + latlong +", verb= "+verb +", timespent= "+timespent
	+"properties= "+properties.toString()+ "]";
	}
}
