package com.wck.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wck.domain.FindIdDTO;
import com.wck.domain.FindPwDTO;
import com.wck.domain.MemberVO;
import com.wck.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/wck/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
	
	private final MemberService memberService;

	/*
	 * author : 왕종휘
	 * 유저 아이디 / 비밀번호 찾기
	 */
	@GetMapping("/findIdPw")
	public String findIdPw(Model model, @RequestParam(name = "tab", defaultValue = "1") Integer tab) {
		model.addAttribute("tab", tab);
		model.addAttribute("findId", new FindIdDTO());
		model.addAttribute("findPw", new FindPwDTO());

		return "wck/member/find_id_pw";
	}

	/*
	 * author : 왕종휘
	 * 유저 아이디 찾기 수행 (post)
	 */
	@PostMapping("/findId")
	public String findIdPost(
			@ModelAttribute("findId") FindIdDTO findIdDto, RedirectAttributes redirect) {
		List<MemberVO> members = memberService.findMemberListByNameAndBirth(findIdDto);
		redirect.addFlashAttribute("members",members);
		return "redirect:/wck/member/findIdComplete";
	}

	/*
	 * author : 왕종휘
	 * 유저 비밀번호 찾기 수행 (post)
	 */
	@PostMapping("/findPw")
	public String findPwPost(
			@ModelAttribute("findPw") FindPwDTO findPwDTO,
			RedirectAttributes redirect) {
		log.info(findPwDTO);
		boolean isExist = memberService.findMemberByNameAndEmail(findPwDTO);
		redirect.addAttribute("isExist",isExist);
		
		// 존재하는 경우 비밀번호 바꾸고 메일을 보냄
		if(isExist) 
			memberService.resetPasswordByEmail(findPwDTO.getEmail());
		return "redirect:/wck/member/findPwComplete";
	}

	/*
	 * author : 왕종휘
	 * 아이디 찾기완료 페이지
	 */
	@GetMapping("/findIdComplete")
	public String findIdComplete() {
		return "wck/member/find_id_comp";
	}

	/*
	 * author : 왕종휘
	 * 비밀번호 찾기 완료 페이지
	 */
	@GetMapping("/findPwComplete")
	public String findPwComplete(@ModelAttribute("isExist") boolean isExist) {
		return "wck/member/find_pw_comp";
	}
}
