package com.spring.mvc.dataTransfer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dataTransfer.dto.MemberDto;

@Controller // 해당 클래스가 Controller임을 스프링 빈에 등록한다.
@RequestMapping("controllerToView") // 중복되는 경로는 Controller위에 url을 설정하여 코드의 중복을 줄일 수 있다.
public class ControllerToView {

	/*
	 * 
	 *  예시 1) Model
	 *  
	 *  - 메서드의 파라미터로 Model을 추가하여 addAddtribute("속성명","값") 메서드로 뷰에서 사용할 데이터를 전송 한다.
	 * 
	 * */
	
	//value에는 url을 작성하고 method는 요청타입을 작성한다. (요청타입을 생략할 경우 GET , POST를 모두 처리한다.)
	@RequestMapping(value="/modelEx" , method=RequestMethod.GET)
	public String modelEx(Model model) {
		
		List<MemberDto> memberList = new ArrayList<MemberDto>();
		for (int i = 1; i < 11; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberId("memberId" + i);
			memberDto.setMemberName("익명" + i);
			memberDto.setHp("010-1234-1234");
			memberDto.setMemberGender("M");
			memberDto.setEmail("anonymous@gmail.com");
			memberDto.setRegidence("서울특별시");
			memberList.add(memberDto);
		}
		
		model.addAttribute("method" , "Model");
		model.addAttribute("memberList" , memberList);
		
		return "dataTransfer/memberList"; // servlet-context.xml에 명시된 대로 포워딩할 jsp파일을 작성해 준다.
	
	}
	
	
	/*
	 * 
	 * 예시 2) ModelAndView
	 * 
	 * - ModelAndView객체를 생성하여  addObject("속성명", "값") 메서드로 뷰에서 사용할 데이터를 전달 한다.
	 * - 관용적으로 객체명으로 mv 혹은 mav로 작성한다.
	 * 
	 * */
	
	@RequestMapping(value="/modelAndViewEx", method=RequestMethod.GET)
	public ModelAndView modelAndViewEx() { // return 타입은 String이 아닌 ModelAndView 클래스로 작성한다.
		
		//ModelAndView mv = new ModelAndView();		// ModelAndView 객체를 생성한다.
		//mv.setViewName("dataTransfer/memberList");  // setViewName() 메서드에 포워딩할 jsp파일을 작성한다.
		
		// setViewName() 메서드대신 생성자의 인수로 jsp페이지명을 지정할 수 있다.
		ModelAndView mv = new ModelAndView("dataTransfer/memberList");
		
		List<MemberDto> memberList = new ArrayList<MemberDto>();
		for (int i = 11; i < 21; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberId("memberId" + i);
			memberDto.setMemberName("익명" + i);
			memberDto.setHp("010-0000-0000");
			memberDto.setMemberGender("F");
			memberDto.setEmail("anonymous@naver.com");
			memberDto.setRegidence("경기도");
			memberList.add(memberDto);
		}
		
		mv.addObject("method" , "ModelAndView");	// addObject() 메서드에 전송할 데이터를 작성한다.
		mv.addObject("memberList" , memberList);
		
		return mv;									// ModelAndView 객체를 반환한다.
		
	}
	

	/*
	 * 
	 * 예시 3) httpServeletRequest
	 * 
	 * - HttpServletRequest객체를 생성하여 setAttribute("속성명", "값") 메서드로 뷰에서 사용할 데이터를 전달 한다.
	 * 
	 * */
	@RequestMapping(value="/requestEx" , method=RequestMethod.GET)
	public String requestEx(HttpServletRequest request) {
		
		List<MemberDto> memberList = new ArrayList<MemberDto>();
		for (int i = 21; i < 31; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberId("memberId" + i);
			memberDto.setMemberName("익명" + i);
			memberDto.setHp("010-7777-7777");
			memberDto.setMemberGender("M");
			memberDto.setEmail("anonymous@gmail.com");
			memberDto.setRegidence("인천");
			memberList.add(memberDto);
		}
		
		request.setAttribute("method" , "Request");
		request.setAttribute("memberList" , memberList);
		
		return "dataTransfer/memberList";
		
	}
	


	/*
	 *
	 *	# 예시 4) ResponseBody  
	 *	
	 *	- ResponseBody와 기능이 같으며 헤더와 상태 코드를 제외한 html 본문만 전송한다.
	 *	- 한글 데이터가 전송이 되지 않으며 한글 전송시 ResponseEntity의 객체의 
	 *	  객체명.add("Content-Type", "text/html; charset=utf-8") 메서드를 이용하여 헤더 정보를 추가하여 사용한다.
	 *
	 */
	
	@RequestMapping(value="/responseBodyEx" , method=RequestMethod.GET)
	public @ResponseBody String responseBodyEx(HttpServletRequest request) {
		
		//String data = "<h1> data </h1>";
		
		String data = "<script>";
			  data += "alert('send message');";
			  data += "location.href='" + request.getContextPath() +"/controllerToView/modelEx'";
			  data += "</script>";
					  
		return data;
		
	}


	/*
	 *
	 *	# 예시 5) ResponseEntity
	 *
	 *	 - HTTP에서 Request와 Response 동작시 전송되는 정보는 크게 body(몸체) , headers(헤더), status code(상태 코드)가 있다.
	 *	 - @ResponseBody 에는 없는 헤더와 상태코드가 함께 들어간다.
	 *	
	 *	1. body (옵션)
	 *	- HTTP의 request 또는 response가 전송하는 데이터(본문)
	 *	
	 *	2. headers (옵션)
	 *	- HTTP의 request 또는 response에 대한 부가적인 정보
	 *	
	 *	3. status code (필수)
	 *	- 클라이언트의 요청이 성공적으로 처리되었는지 상태를 알려주는 것 
	 *
	 *
	 */
	@RequestMapping(value="/responseEntityEx" , method=RequestMethod.GET)
	public ResponseEntity<Object> responseEntityEx() {
		
		// 1)
		//return new ResponseEntity<Object>(HttpStatus.OK);
		
		// 2)
		//return new ResponseEntity<Object>("<h1>html page</h1>" , HttpStatus.OK);
	
		// 3)
		
		String data = "<h1>html 페이지를 반환합니다.</h1>";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type" , "text/html; charset=UTF-8");
		
		return new ResponseEntity<Object>(data , header , HttpStatus.OK);
	
	}
	
	
}

/*
 *
 *	# 예시 6) RestController 이용 
 *	
 *	- ResponseBody와 기능이 같으며 헤더와 상태 코드를 제외한 html 본문만 전송한다.
 *	- 클래스에 @RestController어노테이션을 작성하여 구현한다. 
 *
 */

@RestController
class RestControllerEx {
	
	@RequestMapping(value="/restControllerEx" , method=RequestMethod.GET)
	public String responseBodyEx(HttpServletRequest request) {
		
		//String data = "<h1> data </h1>";
		
		String data = "<script>";
			  data += "alert('html redirect');";
			  data += "location.href='"+ request.getContextPath() + "/controllerToView/modelAndViewEx'";
			  data += "</script>";
					  
		return data;
		
	}
	
	
}










