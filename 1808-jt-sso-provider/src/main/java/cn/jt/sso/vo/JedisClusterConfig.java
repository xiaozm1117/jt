package cn.jt.sso.vo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/*
 * 设置保护模式为no，就无需输入用户名和密码
 * 127.0.0.1:6379> config set protected-mode no
 */

@Configuration
public class JedisClusterConfig {
	@Autowired
	private RedisProperties redisProperties;

	public JedisCluster getJedisCluster() {
		String[] serverArray = redisProperties.getClusterNodes().split(",");
		Set<HostAndPort> nodes = new HashSet<>();

		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));

		}
		return new JedisCluster(nodes, redisProperties.getCommandTimeout());
	}

}
