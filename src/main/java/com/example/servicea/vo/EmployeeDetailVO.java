package com.example.servicea.vo;

import java.util.Date;

public class EmployeeDetailVO {

	String employeeId;
	String insteadApprover;
	Date insteadStartDate;
	Date insteadEndDate;
	String checkAttendanceYn;
	String checkAttendanceMethod;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getInsteadApprover() {
		return insteadApprover;
	}

	public void setInsteadApprover(String insteadApprover) {
		this.insteadApprover = insteadApprover;
	}

	public Date getInsteadStartDate() {
		return insteadStartDate;
	}

	public void setInsteadStartDate(Date insteadStartDate) {
		this.insteadStartDate = insteadStartDate;
	}

	public Date getInsteadEndDate() {
		return insteadEndDate;
	}

	public void setInsteadEndDate(Date insteadEndDate) {
		this.insteadEndDate = insteadEndDate;
	}

	public String getCheckAttendanceYn() {
		return checkAttendanceYn;
	}

	public void setCheckAttendanceYn(String checkAttendanceYn) {
		this.checkAttendanceYn = checkAttendanceYn;
	}

	public String getCheckAttendanceMethod() {
		return checkAttendanceMethod;
	}

	public void setCheckAttendanceMethod(String checkAttendanceMethod) {
		this.checkAttendanceMethod = checkAttendanceMethod;
	}
}