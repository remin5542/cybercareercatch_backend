package com.ccc.qna.dto;

public class QnaCompanyDTO {
	private Long companyNumber;
	private String companyName;

	public QnaCompanyDTO() {
		;
	}

	public Long getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(Long companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "CompanyDTO [companyNumber=" + companyNumber + ", companyName=" + companyName + "]";
	}
}