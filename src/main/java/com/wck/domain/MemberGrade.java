package com.wck.domain;

import lombok.Getter;

@Getter
public enum MemberGrade {
	FRIEND(1),
	MANIA(2),
	STAR(3);
	
	private static final MemberGrade[] GradeArr = MemberGrade.values();
	private final String grade;
	private final int flag;
	private final double accruRate;
	
	MemberGrade(int gradeFlag) {
		if(gradeFlag == 1) {
			this.flag = 1;
			this.grade = "FRIEND";
			this.accruRate = 0.05;
		} else if (gradeFlag == 2) {
			this.flag = 2;
			this.grade = "MANIA";
			this.accruRate = 0.07;
		} else {
			this.flag = 3;
			this.grade = "STAR";
			this.accruRate = 0.1;
		}
	}
	
	public static MemberGrade of(int gradeFlag) {
		if(gradeFlag < 1 || gradeFlag > 3)
			throw new IllegalArgumentException("Invalid gradeFlag : " + gradeFlag);
		return GradeArr[gradeFlag - 1];
	}
	
	
}
