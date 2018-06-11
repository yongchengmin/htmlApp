package com.yc.utils;

public class MyStringUtils {

	  public static boolean isEmpty(String str)
	  {
	    return (str == null) || (str.length() == 0);
	  }

	  public static boolean isNotEmpty(String str)
	  {
	    return !isEmpty(str);
	  }
	  
	  public static String substringBefore(String str, String separator)
	  {
	    if ((isEmpty(str)) || (separator == null)) {
	      return str;
	    }
	    if (separator.length() == 0) {
	      return "";
	    }
	    int pos = str.indexOf(separator);
	    if (pos == -1) {
	      return str;
	    }
	    return str.substring(0, pos);
	  }

	  public static String substringAfter(String str, String separator)
	  {
	    if (isEmpty(str)) {
	      return str;
	    }
	    if (separator == null) {
	      return "";
	    }
	    int pos = str.indexOf(separator);
	    if (pos == -1) {
	      return "";
	    }
	    return str.substring(pos + separator.length());
	  }

	  public static String substringBeforeLast(String str, String separator)
	  {
	    if ((isEmpty(str)) || (isEmpty(separator))) {
	      return str;
	    }
	    int pos = str.lastIndexOf(separator);
	    if (pos == -1) {
	      return str;
	    }
	    return str.substring(0, pos);
	  }

	  public static String substringAfterLast(String str, String separator)
	  {
	    if (isEmpty(str)) {
	      return str;
	    }
	    if (isEmpty(separator)) {
	      return "";
	    }
	    int pos = str.lastIndexOf(separator);
	    if ((pos == -1) || (pos == str.length() - separator.length())) {
	      return "";
	    }
	    return str.substring(pos + separator.length());
	  }

	  public static String substringBetween(String str, String tag)
	  {
	    return substringBetween(str, tag, tag);
	  }

	  public static String substringBetween(String str, String open, String close)
	  {
	    if ((str == null) || (open == null) || (close == null)) {
	      return null;
	    }
	    int start = str.indexOf(open);
	    if (start != -1) {
	      int end = str.indexOf(close, start + open.length());
	      if (end != -1) {
	        return str.substring(start + open.length(), end);
	      }
	    }
	    return null;
	  }
	  public static void main(String[] args) {
		//(6ooo#~~~)第一个#开始截取(从左起)
	    	System.out.println(
	    			MyStringUtils.substringAfter("234556#6ooo#~~~", "#"));
	    	//(~~~)最后一个#开始截取
	    	System.out.println(
	    			MyStringUtils.substringAfterLast("234556#6bbb#~~~", "#"));
	    	//(234556)第一个#往左截取(从左起)
	    	System.out.println(
	    			MyStringUtils.substringBefore("234556#6bbb#~~~", "#"));
	    	//(234556#6bbb)最后一个#往左截取
	    	System.out.println(
	    			MyStringUtils.substringBeforeLast("234556#6bbb#~~~", "#"));
	    	//(6bbb)截取两个#之间的
	    	System.out.println(
	    			MyStringUtils.substringBetween("234556#6bbb#~~~", "#"));
	    	//(6bbb#~)截取第一个#与第一个$之间的(从左起)
	    	System.out.println(
	    			MyStringUtils.substringBetween("$234556#6bbb#~$~~$","#","$"));
	}
}
