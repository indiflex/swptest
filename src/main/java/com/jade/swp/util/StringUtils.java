package com.jade.swp.util;

import java.text.DecimalFormat;

public class StringUtils {
	public static String len2(int n) {
		return new DecimalFormat("00").format(n).toString();
	}
}
