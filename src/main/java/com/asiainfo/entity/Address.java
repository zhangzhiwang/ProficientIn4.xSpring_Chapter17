package com.asiainfo.entity;

public class Address {
	private String tel;
	private String zoneCode;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	@Override
	public String toString() {
		return "Address [tel=" + tel + ", zoneCode=" + zoneCode + "]";
	}

}
