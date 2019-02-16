package cn.jt.sso.service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jt.sso.mapper.UserMapper;
import cn.jt.sso.vo.JedisClusterConfig;
import cn.jt.sso.vo.RedisClientTemplate;
import cn.tedu.ssm.pojo.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	RedisClientTemplate redisClientTemplate;
	@Autowired UserMapper userMapper;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Map<Integer,String>map=new HashMap<Integer,String>();
	static {
		map.put(1, "username");
		map.put(2, "phone");
		map.put(3, "email");
	}
	public Boolean check(String param,int type) {
		EntityWrapper<User>wrapper=new EntityWrapper<>();
		wrapper.where(map.get(type)+"={0}", param);
		int i=userMapper.selectCount(wrapper);
		if(i==0) {
			return false;
		}else {
			return true;
		}
	}
	
	public String register(User user) {
		
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userMapper.insert(user);
		return user.getUsername();
		
	}
	
	
	public String login(String username,String password) {
		String ticket = null;
		//1.按用户名进行查询，获取user对象
		User param = new User();		//查询条件
		param.setUsername(username);
		
		User user = userMapper.selectOne(param);
		if(user!=null) {
			//2.数据库获取的user.password（加密）和页面接收到password（明码）加密比较
			String newPassword = DigestUtils.md5Hex(password);
			if(user.getPassword().equals(newPassword)) {
				try {
					//3.如果是系统用户，产生ticket=redis.key，value=user.json
					//ticket 要求：a.动态性，b.唯一性，c.混淆性
					ticket = DigestUtils.md5Hex("JT_TICKERT_"+System.currentTimeMillis() + username);
					
					//4.写redis，设置过期时间7天
					String userJson = MAPPER.writeValueAsString(user);
					redisClientTemplate.setToRedis(ticket, userJson);		//不用，编译时编译器优化，会自动计算出来
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ticket;
	}
	
	public String queryByTicket(String ticket){
		String xiaoString = null;
		try {
			
			xiaoString=MAPPER.writeValueAsString(redisClientTemplate.getRedis(ticket));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xiaoString;
	}
}
