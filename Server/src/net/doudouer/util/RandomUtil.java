package net.doudouer.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随即工具类
 * @author congteng
 */
public class RandomUtil {
	private static Random random = new Random();
	public RandomUtil() {

	}
	/**
	 * 通过传入的i的值，从所有的id中随机选出i个 
	 */
	public static Object[] getRandomIdsFromAllIdsByCount(List<Object> ids, int i) {
		Object[] result = new Long[i];
		List<Integer> cursors= new ArrayList<Integer>();
		
		int len = ids.size();
		Random random = new Random();
		
		while(cursors.size() != i){
			int cursor = random.nextInt(len);
			if(!cursors.contains(cursor)){
				cursors.add(cursor);
			}
		}
		
		for(int j=0; j<cursors.size(); j++){
			result[j] = ids.get(cursors.get(j));
		}
		
		return result;
	}
	
	public static List<Object> generateFiguresByLengh(int length){
		List<Object> retVal = new ArrayList<Object>();
		for(Long i = 0L; i < length; i++){
			retVal.add(i);
		}
		return retVal;
	}
}