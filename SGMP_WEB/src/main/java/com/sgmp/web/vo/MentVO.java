package com.sgmp.web.vo;

import org.springframework.stereotype.Repository;

@Repository
public class MentVO {
	private int NO_BID;
	private int Ment_ID;
	private String MENT_CONTENT;
	private String MENT_WRITER;
	private String Ment_DATE;



	public int getNO_BID() {
		return NO_BID;
	}

	public void setNO_BID(int nO_BID) {
		NO_BID = nO_BID;
	}

	public int getMent_ID() {
		return Ment_ID;
	}

	public void setMent_ID(int ment_ID) {
		Ment_ID = ment_ID;
	}

	public String getMENT_CONTENT() {
		return MENT_CONTENT;
	}

	public void setMENT_CONTENT(String mENT_CONTENT) {
		MENT_CONTENT = mENT_CONTENT;
	}

	public String getMENT_WRITER() {
		return MENT_WRITER;
	}

	public void setMENT_WRITER(String mENT_WRITER) {
		MENT_WRITER = mENT_WRITER;
	}

	public String getMent_DATE() {
		return Ment_DATE;
	}

	public void setMent_DATE(String ment_DATE) {
		Ment_DATE = ment_DATE;
	}

	@Override
	public String toString() {
		return "MentVO [NO_BID=" + NO_BID + ", Ment_ID=" + Ment_ID + ", MENT_CONTENT=" + MENT_CONTENT + ", MENT_WRITER="
				+ MENT_WRITER + ", Ment_DATE=" + Ment_DATE + "]";
	}


}
