package com.asiainfo.entity;

import org.hibernate.validator.constraints.Length;

public class Dept {
	private String deprId;
	@Length(min=1, max=3)
	private String deptName;
	private Address address;

	public String getDeprId() {
		return deprId;
	}

	public void setDeprId(String deprId) {
		this.deprId = deprId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Dept [deprId=" + deprId + ", deptName=" + deptName + ", address=" + address + "]";
	}

}
