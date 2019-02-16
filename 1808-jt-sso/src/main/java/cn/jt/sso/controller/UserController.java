package cn.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jt.common.vo.SysResult;
import cn.jt.sso.feign.UserFeign;

@Controller

public class UserController {
	@Autowired
	private UserFeign userFeign;
	
	
	@RequestMapping("/{index}")
	
	public String index(@PathVariable String index){
		return index;
	}
	@RequestMapping("/result/{abc}")
	@ResponseBody
	public String result(@PathVariable String abc){
		return abc;
	}
	
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public SysResult check(@PathVariable String param,@PathVariable Integer type){
		return userFeign.check(param, type);
	}
	
	@RequestMapping("/user/register")
	@ResponseBody
	public SysResult regiester(String username, String password, String phone, String email) {
		return userFeign.register(username, password, phone, email);
	}
	
	@RequestMapping("/user/login")
	@ResponseBody
	public SysResult  login(@RequestParam("u") String username,
			@RequestParam("p") String password) {
		return userFeign.login(username, password);
	}
	
	@RequestMapping("/user/query/{ticket}")
	@ResponseBody
	public SysResult queryByTicket(@PathVariable String ticket) {
		return userFeign.queryByTicket(ticket);
	}
}	






