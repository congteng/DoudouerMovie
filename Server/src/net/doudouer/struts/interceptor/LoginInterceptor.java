package net.doudouer.struts.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 拦截器
 */
public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 2848720530124435630L;
	
	public void destroy() {
	}
	public void init() {
	}
	
	public String intercept(ActionInvocation invocation) throws Exception {
		return invocation.invoke();
	}
}
