package com.jade.swp.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Ignore;
import org.junit.Test;

public class StringUtilsTest {
	private String str = "Abcd1234Efg";
	
	private int[] array = new int[] { 2, 3, 8, 7, 5 };
	private Integer[] array2 = new Integer[] { 2, 3, 8, 7, 5 };
	
	private Date today = new Date();
	
	private String[][] people = {
			{"chan", "faithful"},
			{"sanga", "pretty"},
			{"jungsoo", "off the wall"},
			{"kyungmin", "smart"}
		};
	
	private Map<Object, Object> map = ArrayUtils.toMap(people);
	
	@Ignore @Test
	public void testNumberUtils() {
		int min = NumberUtils.min(array);
		int max = NumberUtils.max(array);
		System.out.println("min: " + min);
		System.out.println("max: " + max);
		
		String[] ss = ArrayUtils.toStringArray(array2);
		String nstr = String.join("", ss);
		System.out.println("nstr: " + nstr);
		Number n = NumberUtils.createNumber(nstr);
		System.out.println("n=" + n + ", " + (n.intValue() + 3) + ", ");
		System.out.println("toInt: " + NumberUtils.toInt(nstr));
		System.out.println("join: " + StringUtils.join(array2, "|"));
		System.out.println("isNumber: " + NumberUtils.isNumber(nstr) + ", " + StringUtils.isNumeric(nstr));
	}
	
	@Ignore @Test
	public void testStringUtils() {
		String[] sts = new String[map.size()];
		map.keySet().toArray(sts);
		System.out.println("join: " + StringUtils.join(sts, "|"));
	}
	
	
	
	@Ignore @Test
	public void testDateUtils() {
		Date yesterday = DateUtils.addDays(today, -1);
		Date tomorrow = DateUtils.addDays(today, 1);
		System.out.println("Today: " + today);
		System.out.println("yesterday: " + yesterday);
		System.out.println("tomorrow: " + tomorrow);
		System.out.println("Next Hour: " + DateUtils.addHours(today, 1));
		System.out.println("isSameDay: " + DateUtils.isSameDay(today, Calendar.getInstance().getTime()));
	}

	@Ignore @Test
	public void testArrayUtils() {
		System.out.println("Arrays: " + Arrays.toString(people));
		System.out.println("ArrayUtils: " + ArrayUtils.toString(people));
		
		System.out.println("Chan is " + map.get("chan"));
		
		
		System.out.println("toString: " + Arrays.toString(array) + " vs " + ArrayUtils.toString(array));
		ArrayUtils.reverse(array);
		System.out.println("Reverse:" + ArrayUtils.toString(array));
		Arrays.sort(array);
		System.out.println("Sort: " + Arrays.toString(array));
		ArrayUtils.reverse(array);
		System.out.println("Reverse: " + ArrayUtils.toString(array));
		
		System.out.println("Contains 9: " + ArrayUtils.contains(array, 9));
		System.out.println("Contains 8: " + ArrayUtils.contains(array, 8));
	}

}
