package com.hx.dmcp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author krisjin
 * @date 2014-2-1下午2:12:07
 */
public class HtmlUtil {
	private static final String REGXP_HTML_TAG = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

	private static final String REGXP_FOR_IMG_TAG = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签

	private static final String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

	private static final String REGXP_SRCATR_VALUE = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";

	private HtmlUtil() {
	}

	/**
	 * 替换标记以正常显示
	 * 
	 * @param input
	 * @return String
	 */
	public String replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}

		}
		return (filtered.toString());
	}

	/**
	 * 
	 * 基本功能：判断标记是否存在
	 * <p>
	 * 
	 * @param input
	 * @return boolean
	 */
	public boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * 基本功能：过滤所有以"<"开头以">"结尾的标签
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String filterHtml(String str) {
		Pattern pattern = Pattern.compile(REGXP_HTML_TAG);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 
	 * 基本功能：过滤指定标签
	 * <p>
	 * 
	 * @param str
	 * @param tag
	 *            指定标签
	 * @return String
	 */
	public static String fiterHtmlTag(String str, String tag) {
		String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
		Pattern pattern = Pattern.compile(regxp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 
	 * 基本功能：替换指定的标签
	 * <p>
	 * 
	 * @param str
	 * @param beforeTag
	 *            要替换的标签
	 * @param tagAttrib
	 *            要替换的标签属性值
	 * @param startTag
	 *            新标签开始标记
	 * @param endTag
	 *            新标签结束标记
	 * @return String
	 * @如：替换img标签的src属性值为[img]属性值[/img]
	 */
	public static String replaceHtmlTag(String str, String beforeTag, String tagAttrib, String startTag, String endTag) {
		String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result) {
			StringBuffer sbreplace = new StringBuffer();
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
			if (matcherForAttrib.find()) {
				matcherForAttrib.appendReplacement(sbreplace, startTag + matcherForAttrib.group(1) + endTag);
			}
			matcherForTag.appendReplacement(sb, sbreplace.toString());
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}

	/**
	 * Gets Single Img Tag Attribute
	 * 
	 * @param input
	 * @return
	 */
	public static String getImgAtr(String input) {
		Pattern p = Pattern.compile(REGXP_SRCATR_VALUE);
		Matcher matcher = p.matcher(input);
		String retVal = null;
		boolean result = matcher.find();
		while (result) {
			retVal = matcher.group(1);
			result = matcher.find();
		}
		return retVal;
	}

	public static List getMutiImgAtr(String input) {
		Pattern p = Pattern.compile(REGXP_SRCATR_VALUE);
		Matcher matcher = p.matcher(input);
		List<String> list = new ArrayList<String>();
		boolean flag = matcher.find();

		while (flag) {
			list.add(matcher.group(1));
			flag = matcher.find();
		}
		return list;
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param content
	 * @param length
	 * @return
	 */
	public static String subStrByte(String content, int length) {
		content = content.trim();
		int strByteLength = 0;
		if (null == content || "".equals(content))
			return "";
		strByteLength = content.getBytes().length;
		if (strByteLength < length)
			return content;
		for (int i = 0; i < length; i++) {
			String tmp = content.substring(i, i + 1);
			if (tmp.getBytes().length > 2)
				length = length - 2;
		}
		return content.substring(0, length);
	}
}
