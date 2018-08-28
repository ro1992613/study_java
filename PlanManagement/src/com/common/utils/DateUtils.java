package com.common.utils;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 严格的日期转换setLenient(false);
 * setLenient
 * public void setLenient(boolean lenient)指定日期/时间解析是否不严格。进行不严格解析时，解析程序可以使用启发式的方法来解释与此对象的格式不精确匹配的输入。进行严格解析时，输入必须匹配此对象的格式。 
 * 参数：
 * lenient - 为 true 时，解析过程是不严格的
 * 不会自动将错误日期转换为正确的日期
 * 例如:19450000,使用原DateUtil会转换为19441130
 * @author liuzh
 */
public class DateUtils {
	public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";
	public static final String YM = "yyyyMM";
	public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd";
	public static final String NORMAL_DATE_FORMAT_NEW = "yyyy-mm-dd hh24:mi:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_ALL = "yyyyMMddHHmmssS";
	private static Logger logger = Logger.getLogger(DateUtils.class);
	/**
	 * 2016-12-2 14:11:00 转换为 2016-12-02 14:11:00
	 * @param paramString
	 * @return
	 * @throws Exception
	 */
	public static String strToFormatStr(String paramString) throws Exception {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = localSimpleDateFormat.parse(paramString);
		return localSimpleDateFormat.format(date);
	}

	public static Long strDateToNum(String paramString) throws Exception {
		if (paramString == null)
			return null;
		String[] arrayOfString = null;
		String str = "";
		if (paramString.indexOf("-") >= 0) {
			arrayOfString = paramString.split("-");
			for (int i = 0; i < arrayOfString.length; ++i)
				str = str + arrayOfString[i];
			return Long.valueOf(Long.parseLong(str));
		}
		return Long.valueOf(Long.parseLong(paramString));
	}

	public static Long strDateToNum1(String paramString) throws Exception {
		if (paramString == null)
			return null;
		String[] arrayOfString = null;
		String str = "";
		if (paramString.indexOf("-") >= 0) {
			arrayOfString = paramString.split("-");
			for (int i = 0; i < arrayOfString.length; ++i)
				if (arrayOfString[i].length() == 1)
					str = str + "0" + arrayOfString[i];
				else
					str = str + arrayOfString[i];
			return Long.valueOf(Long.parseLong(str));
		}
		return Long.valueOf(Long.parseLong(paramString));
	}

	public static String numDateToStr(Long paramLong) {
		if (paramLong == null)
			return null;
		String str = null;
		str = paramLong.toString().substring(0, 4) + "-"
				+ paramLong.toString().substring(4, 6) + "-"
				+ paramLong.toString().substring(6, 8);
		return str;
	}

	public static Date stringToDate(String paramString1,
			String paramString2)  {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				paramString2);
		localSimpleDateFormat.setLenient(false);
		try {
			return localSimpleDateFormat.parse(paramString1);
		} catch (Exception localParseException) {
			localParseException.printStackTrace();
			return null;
		}
	}

	public static String dateToString(Date paramDate,
			String paramString) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				paramString);
		localSimpleDateFormat.setLenient(false);
		return localSimpleDateFormat.format(paramDate);
	}

	public static Date compactStringToDate(String paramString)
			throws Exception {
		return stringToDate(paramString, "yyyyMMdd");
	}

	public static String dateToCompactString(Date paramDate) {
		return dateToString(paramDate, "yyyyMMdd");
	}

	public static String dateToNormalString(Date paramDate) {
		return dateToString(paramDate, "yyyy-MM-dd");
	}

	public static String compactStringDateToNormal(String paramString)
			throws Exception {
		return dateToNormalString(compactStringToDate(paramString));
	}

	public static int getDaysBetween(Date paramDate1,
			Date paramDate2) throws Exception {
		Calendar localCalendar1 = Calendar.getInstance();
		Calendar localCalendar2 = Calendar.getInstance();
		localCalendar1.setTime(paramDate1);
		localCalendar2.setTime(paramDate2);
		if (localCalendar1.after(localCalendar2))
			throw new Exception("起始日期小于终止日期!");
		int i = localCalendar2.get(6) - localCalendar1.get(6);
		int j = localCalendar2.get(1);
		if (localCalendar1.get(1) != j) {
			localCalendar1 = (Calendar) localCalendar1.clone();
			do {
				i += localCalendar1.getActualMaximum(6);
				localCalendar1.add(1, 1);
			} while (localCalendar1.get(1) != j);
		}
		return i;
	}

	public static Date addHours(Date paramDate, int hours){
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		localCalendar.add(Calendar.HOUR_OF_DAY, hours);
		return localCalendar.getTime();
	}
	
	public static Date addDays(Date paramDate, int paramInt){
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		int i = localCalendar.get(6);
		localCalendar.set(6, i + paramInt);
		return localCalendar.getTime();
	}
	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	public static Date addDays(String paramString1,
			String paramString2, int paramInt) throws Exception {
		Calendar localCalendar = Calendar.getInstance();
		Date localDate = stringToDate(paramString1, paramString2);
		localCalendar.setTime(localDate);
		int i = localCalendar.get(6);
		localCalendar.set(6, i + paramInt);
		return localCalendar.getTime();
	}

	public static java.sql.Date getSqlDate(Date paramDate)
			throws Exception {
		java.sql.Date localDate = new java.sql.Date(paramDate.getTime());
		return localDate;
	}

	public static String formatDate(Date paramDate) {
		if (paramDate == null)
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		localSimpleDateFormat.setLenient(false);
		return localSimpleDateFormat.format(paramDate);
	}

	public static String formatDateTime(Date paramDate) {
		/*if (paramDate == null)
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		localSimpleDateFormat.setLenient(false);
		return localSimpleDateFormat.format(paramDate);*/
		return formatDateTime(paramDate, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String formatDateTime(Date paramDate,String format) {
		if (paramDate == null)
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(format);
		localSimpleDateFormat.setLenient(false);
		return localSimpleDateFormat.format(paramDate);
	}

	public static Date parseDate(String paramString)
			throws Exception {
		if ((paramString == null) || (paramString.trim().equals("")))
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		localSimpleDateFormat.setLenient(false);
		try {
			return localSimpleDateFormat.parse(paramString);
		} catch (Exception localParseException) {
			//throw new Exception("日期解析出错！", localParseException);
			logger.error(localParseException.getLocalizedMessage(),localParseException);
			return null;
		}
	}

	public static Date parseDateTime(String paramString) {
		if ((paramString == null) || (paramString.trim().equals("")))
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		localSimpleDateFormat.setLenient(false);
		try {
			return localSimpleDateFormat.parse(paramString);
		} catch (Exception localParseException) {
		}
		return null;
	}

	public static Integer getYM(String paramString) throws Exception {
		if (paramString == null)
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		localSimpleDateFormat.setLenient(false);
		Date localDate;
		try {
			localDate = localSimpleDateFormat.parse(paramString);
		} catch (Exception localParseException) {
			throw new Exception("时间解析异常！", localParseException);
		}
		return getYM(localDate);
	}

	public static Integer getYM(Date paramDate) {
		if (paramDate == null)
			return null;
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		int i = localCalendar.get(1);
		int j = localCalendar.get(2) + 1;
		return new Integer(i * 100 + j);
	}

	public static int addMonths(int paramInt1, int paramInt2) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.set(1, paramInt1 / 100);
		localCalendar.set(2, paramInt1 % 100 - 1);
		localCalendar.set(5, 1);
		localCalendar.add(2, paramInt2);
		return getYM(localCalendar.getTime()).intValue();
	}

	public static Date addMonths(Date paramDate,
			int paramInt) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		localCalendar.add(2, paramInt);
		return localCalendar.getTime();
	}

	public static int monthsBetween(int paramInt1, int paramInt2) {
		int i = paramInt2 / 100 * 12 + paramInt2 % 100
				- (paramInt1 / 100 * 12 + paramInt1 % 100);
		return i;
	}

	public static int monthsBetween(Date paramDate1,
			Date paramDate2) {
		return monthsBetween(getYM(paramDate1).intValue(), getYM(paramDate2).intValue());
	}

	public static String getChineseDate(Calendar paramCalendar) {
		int i = paramCalendar.get(1);
		int j = paramCalendar.get(2);
		int k = paramCalendar.get(5);
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append(i);
		localStringBuffer.append("年");
		localStringBuffer.append(j + 1);
		localStringBuffer.append("月");
		localStringBuffer.append(k);
		localStringBuffer.append("日");
		return localStringBuffer.toString();
	}
	
	public static Date addDateMinut(Date date, int x)
	 {   
	        Calendar cal = Calendar.getInstance();   
	        cal.setTime(date);   
	        cal.add(Calendar.MINUTE, x);// 24小时制   
	        date = cal.getTime();   
	        cal = null;   
	        return date;  
	 }

	public static String getChineseWeekday(Calendar paramCalendar) {
		switch (paramCalendar.get(7)) {
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		case 1:
			return "星期日";
		}
		return "未知";
	}
	
	public static Date timestampToDate(Timestamp tt){  
	    return new Date(tt.getTime());  
	}  
	
	public static Timestamp dateToTimestamp(Date date){
		return new Timestamp(date.getTime());
	}

	public static String getInterval(Date createAt) {
		// 定义最终返回的结果字符串。
		String interval = null;

		long millisecond = new Date().getTime() - createAt.getTime();

		long second = millisecond / 1000;

		if (second <= 0) {
			second = 0;
		}
		//*--------------微博体（标准）
		if (second == 0) {
			interval = "刚刚";
		} else if (second < 30) {
			interval = second + "秒以前";
		} else if (second >= 30 && second < 60) {
			interval = "半分钟前";
		} else if (second >= 60 && second < 60 * 60) {//大于1分钟 小于1小时
			long minute = second / 60;
			interval = minute + "分钟前";
		} else if (second >= 60 * 60 && second < 60 * 60 * 24) {//大于1小时 小于24小时
			long hour = (second / 60) / 60;
			if (hour <= 3) {
				interval = hour + "小时前";
			} else {
				interval = "今天" + getFormatTime(createAt, "HH:mm");
			}
		} else if (second >= 60 * 60 * 24 && second <= 60 * 60 * 24 * 2) {//大于1D 小于2D
			interval = "昨天" + getFormatTime(createAt, "HH:mm");
		} else if (second >= 60 * 60 * 24 * 2 && second <= 60 * 60 * 24 * 7) {//大于2D小时 小于 7天
			long day = ((second / 60) / 60) / 24;
			interval = day + "天前";
		} else if ( second <= 60 * 60 * 24 * 365 && second >= 60 * 60 * 24 * 7) {//大于7天小于365天
			interval = getFormatTime(createAt, "MM-dd HH:mm");
		} else if (second >= 60 * 60 * 24 * 365) {//大于365天
			interval = getFormatTime(createAt, "yyyy-MM-dd HH:mm");
		} else {
			interval = "0";
		}
		return interval;
	}
	public static String getFormatTime(Date date, String Sdf) {
		return (new SimpleDateFormat(Sdf)).format(date);
	}

	/**
	 * 格式化新浪微博时间
	 * @param createdAt
	 * @return
	 */
	public static String formatSinaDateTime(String createdAt){
		if(createdAt.length()==16){
			return createdAt+":00";
		}else if(createdAt.length()==11){
			return Calendar.getInstance().get(Calendar.YEAR)+"-"+createdAt+":00";
		} else if (createdAt.contains("今天")) {
			return createdAt.replace("今天",formatDate(new Date()))+":00";
		}else if(createdAt.contains("分钟前")){
			return DateUtils.formatDateTime(DateUtils.addDateMinut(new Date(),-Integer.parseInt(createdAt.replace("分钟前",""))));
		}
		return "";
	}
	}