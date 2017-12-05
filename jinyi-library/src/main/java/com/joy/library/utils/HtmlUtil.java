package com.joy.library.utils;

public class HtmlUtil {
	
	public static String formatHtml(String value)
	{
		value=value.replace("&amp;", "&")
				.replace("&lt;", "<")
				.replace("&gt;", ">")
				.replace("&apos;", "'")
				.replace("&quot;", "\"")
				.replace("&nbsp;", " ")
				.replace("&copy;", "©")
				.replace("&reg", "®");
		
		return value;
	}

}
