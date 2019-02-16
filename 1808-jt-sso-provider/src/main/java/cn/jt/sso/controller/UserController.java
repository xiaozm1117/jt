package cn.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jt.sso.service.UserService;
import cn.jt.sso.vo.RedisClientTemplate;
import cn.jt.sso.vo.SysResult;
import cn.tedu.ssm.pojo.User;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/user/check/{param}/{type}")
	public SysResult check(@PathVariable String param, @PathVariable Integer type) {
		try {
			Boolean b = userService.check(param, type);
			if (b) {
				return SysResult.build(201, "用户，电话，邮箱已存在");
			} else {
				return SysResult.ok(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return SysResult.build(201, "检查出错");
		}
	}

	@RequestMapping("/user/register")
	public SysResult register(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("phone") String phone, @RequestParam("email") String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		String use = userService.register(user);
		return SysResult.ok(use);
	}
	@RequestMapping("/user/login")	//重命名username，u
	public SysResult login(@RequestParam("u") String username, 
			@RequestParam("p") String password) {
		String ticket = userService.login(username, password);
		return SysResult.ok(ticket);
	}
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult queryByTicket(@PathVariable String ticket) {
		String userJson=userService.queryByTicket(ticket);
		return SysResult.ok(userJson);
	}

}
