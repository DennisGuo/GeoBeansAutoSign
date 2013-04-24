package guo.autosign.global;

import java.util.List;

/**
 * 用于存储用户信息
 * @author guo
 *
 */
public class UserDataBase {
	public static List<User> users;
	
	static{
		UserDataBase.users.add(new User("0276", "123456"));
	}
}
