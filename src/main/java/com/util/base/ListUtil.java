package com.util.base;

import java.util.ArrayList;
import java.util.List;


public class ListUtil {
	/**
	 * list拆分成多个list，每个list的条数相差不多
	 * @param allList
	 * @return
	 */
	public static <T> List<List<T>> splitByAverage(List<T> allList,int listSize){
		List<List<T>> lists = new ArrayList<List<T>>();

		// =====================lists初初始化，data拆分为lists========================
		for (int i = 0; i < listSize; i++) {
			lists.add(new ArrayList<>());
		}
		for (int i = 0; i < allList.size(); i++) {
			lists.get(i % listSize).add(allList.get(i));
		}
		return lists;
	}
	/**
	 *  list拆分成多个list，每个list的条数不能超过固定条数
	 * @param allList
	 * @param maxListSize
	 * @return
	 */
	public static <T> List<List<T>> splitByLTMax(List<T> allList,int maxListSize){
		List<List<T>> lists = new ArrayList<List<T>>();
		lists.add(new ArrayList<T>());
		for (T model : allList) {
			int lastIndex=lists.size()-1;
			if(lists.get(lastIndex).size()>=maxListSize) {
				lists.add(new ArrayList<T>());
				lastIndex++;
			}
			lists.get(lastIndex).add(model);
		}
		return lists;
	}

}
