package com.spring.mvc.dataTransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.dataTransfer.dao.DataTransferDao;

@Controller
@RequestMapping("mapperToDao")
public class MapperToDao {

	@Autowired
	private DataTransferDao dataTransferDao;
	
	@RequestMapping(value="/selectData1" , method=RequestMethod.GET)
	public String selectData1() {
		dataTransferDao.selectData1();
		return "home";
	}
	
	@RequestMapping(value="/selectData2" , method=RequestMethod.GET)
	public String selectData2() {
		dataTransferDao.selectData2();
		return "home";
	}
	
	@RequestMapping(value="/selectData3" , method=RequestMethod.GET)
	public String selectData3() {
		dataTransferDao.selectData3();
		return "home";
	}
	
	@RequestMapping(value="/selectData4" , method=RequestMethod.GET)
	public String selectData4() {
		dataTransferDao.selectData4();
		return "home";
	}
	
	@RequestMapping(value="/selectData5" , method=RequestMethod.GET)
	public String selectData5() {
		dataTransferDao.selectData5();
		return "home";
	}
	
}
