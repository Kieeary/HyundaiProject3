
package com.wck.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.EventCouponVO;
import com.wck.domain.FindPwDTO;
import com.wck.domain.MemberVO;
import com.wck.domain.UpdateMemberDTO;

@Mapper
public interface MemberMapper {

	public MemberVO findOneByEmail(String email, String loginType);
	public int insertOne(MemberVO member);
	public List<MemberVO> findAllByNameAndBirth(String name, Date birth);
	public MemberVO findOneByNameAndEmail(FindPwDTO findPw);
	public int updatePasswordOne(String email, String pw);
	public int updateInfoOne(UpdateMemberDTO member);
	public int disableOne(String email);
	public int getGradeById(String mId);
	
	public int getCartCount(String mId);
	public List<EventCouponVO> getCouponByEmail(String email);
}

