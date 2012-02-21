package net.doudouer.struts.action;

import javax.annotation.Resource;

import net.doudouer.domain.User;
import net.doudouer.service.UserService;
import net.doudouer.util.ValidateUtil;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User>{

	private static final long serialVersionUID = -710969432261209301L;

	private User model = new User();
	@Resource(name = "userService")
	private UserService userService;
	
	@Override
	public User getModel() {
		return model;
	}
	
	public String doReg(){
		userService.saveEntity(model);
		return this.SUCCESS;
	}
	
	public void validateDoReg(){
		if(!ValidateUtil.isValid(model.getEmail())){
			addFieldError("email", "邮件地址不可为空");
		}
		if(!ValidateUtil.isValid(model.getPassword())){
			addFieldError("password", "密码不可为空");
		}
		if(hasFieldErrors()){
			return;
		}
	}
	
}
