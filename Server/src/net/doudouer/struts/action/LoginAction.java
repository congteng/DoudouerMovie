package net.doudouer.struts.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.doudouer.domain.User;
import net.doudouer.service.UserService;
import net.doudouer.util.ValidateUtil;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{
	
	private static final long serialVersionUID = -6136209069644949776L;

	private User model = new User();
	
	@Resource(name = "userService")
	private UserService userService;

	private Map<String, Object> sessionMap;
	

	public User getModel() {
		return model;
	}
	
	public String doLogin(){
		
		// 首次登录，到数据库查询
		List<User> list = userService.findEntityByHQL("from User u where u.email = ? and u.password = ?", model.getEmail(), model.getPassword());
		if(ValidateUtil.isValid(list)){
			User user = list.get(0);
			
			sessionMap.put("user", user);
			
			return this.SUCCESS;
		}else{
			return "toReg";
		}
	}

	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

}
