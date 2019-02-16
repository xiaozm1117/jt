package cn.jt.sso.service;

import cn.tedu.ssm.pojo.User;

public interface UserService {
	public Boolean check(String param,int type);
	public String register(User user);
	public String login(String username,String password);
	public String queryByTicket(String ticket);
}
