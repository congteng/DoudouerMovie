package net.doudouer.util;

import java.util.List;

public class ValidateUtil {

	public static boolean isValid(String cnd){
		if(cnd == null || "".equals(cnd.trim())){
			return false;
		}else{
			return true;
		}
	}

	public static boolean isValid(List<?> list) {
		if(list == null || list.size() == 0){
			return false;
		}else{
			return true;
		}
	}
	
	
}
