package com.xiakee.service.urm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 在这个类中，你就可以从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等，我想这么简单的代码就不再多解释了 Title:
 * MyUserDetailService.java Description: 主要为了学习计划而建立的代码测试工程 Copyright: Copyright
 * (c) 2011 Company: http://guxinghanshe.com Makedate:2012-3-12 下午3:51:01
 * 
 * @author 谢坚柏
 * @Email xiejianbo8@gmail.com
 * @version %I%, %G%
 * @since 1.0
 */
public class MyUserDetailService implements LoginUserDetailsService {
	private static Logger log = Logger.getLogger(MyUserDetailService.class);
	
//	private static Map<String, User> userMap = new HashMap<String, User>();
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username,String password)
			throws UsernameNotFoundException, DataAccessException {
		log.info("扩展后的用户信息功能成功进来");
		return this.userService.findUserBeanByloginName(username, password);
	}
	
//	private static User getUser(String username){
//		User user = null;
//		if(userMap.size() < 1){
//			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//			GrantedAuthorityImpl auth1 = new GrantedAuthorityImpl("ROLE_BIT");
//			GrantedAuthorityImpl auth2 = new GrantedAuthorityImpl("ROLE_ADMIN");
//			auths.add(auth1);
//			auths.add(auth2);
//			user = new SecurityUser("bit", "guxinghanshe", true, true, true, true, auths,1L,"谢坚柏","13401190238");
//			userMap.put("bit", user);
//			user = new SecurityUser("huangzhaoshui", "xiakee.com", true, true, true, true, auths,1L,"黄召水","18810253590");
//			userMap.put("huangzhaoshui", user);
//			user = new SecurityUser("hulingli", "xiakee168@celeste", true, true, true, true, auths,2L,"胡灵丽","13401190238");
//			userMap.put("hulingli", user);
//			user = new SecurityUser("hunk", "xiakee168@hunk", true, true, true, true, auths,3L,"杨洪","13401190238");
//			userMap.put("hunk", user);
//			user = new SecurityUser("alex", "xiakee168@alex", true, true, true, true, auths,4L,"杨綦","13401190238");
//			userMap.put("alex", user);
//			user = new SecurityUser("pengyang", "xiakee168@pengyang", true, true, true, true, auths,5L,"彭杨","13401190238");
//			userMap.put("pengyang", user);
//		}
//
//		// User(String username, String password, boolean enabled, boolean
//		// accountNonExpired,
//		// boolean credentialsNonExpired, boolean accountNonLocked,
//		// Collection<GrantedAuthority> authorities) {
//		return userMap.get(username);
//	}
}
