package guo.tool;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class CalendarHelp {
	public static Date getTime(){
		Calendar cal=Calendar.getInstance(); 
		return cal.getTime();
	}
	public static Date getDate(){
		try{
			int y,m,d;
			Calendar cal=Calendar.getInstance(); 
			y=cal.get(Calendar.YEAR); 
			m=cal.get(Calendar.MONTH)+1; 
			d=cal.get(Calendar.DATE); 
			String dateStr=y+"-"+m+"-"+d;
			DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(dateStr);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getDateStr(){
		int y,m,d;
		Calendar cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH)+1; 
		d=cal.get(Calendar.DATE); 
		String dateStr=y+"年"+m+"月"+d+"日";
		return dateStr;
	}
	
	public static String getTimeStr1(){
		int h,mi,s;
		Calendar cal=Calendar.getInstance(); 
		h=cal.get(Calendar.HOUR_OF_DAY); 
		mi=cal.get(Calendar.MINUTE)+1; 
		s=cal.get(Calendar.SECOND); 
		String TimeStr1=h+":"+mi+":"+s;
		return TimeStr1;
	}
	
	public static String getNewTimeStr(){
		int y,m,d;
		Calendar cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH)+1; 
		d=cal.get(Calendar.DATE); 
		String yStr=y<10?("0"+y):String.valueOf(y);
		String mStr=m<10?("0"+m):String.valueOf(m);
		String dStr=d<10?("0"+d):String.valueOf(d);
		String time=yStr+"-"+mStr+"-"+dStr;
		return time;
	}
	
	//上月
	public static String getLastMonthTimeStr(){
		int y,m,d;
		Calendar cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH); 
		d=cal.get(Calendar.DATE); 
	    String time=y+"-"+m+"-"+d;
	    return time;
	}
	//上周日期
	public static String getLastWeekTimeStr(){
		int year,day,month;
		Calendar cal=Calendar.getInstance();
		year=cal.get(Calendar.YEAR);
		day=cal.get(Calendar.DATE);
		month=cal.get(Calendar.MONTH);
		String date="";
		if((day-7)<=0)
		{
		if(month==0)
		{
		date=(year-1)+"-12-"+(31-(7-day));
		}
		else{
		//前一个月如果是30天 
		if(month==11||month==9||month==6||month==4)
		{
		date=year+"-"+(month)+"-"+(30-(7-day));
		}
		//前一个月是31天 
		else if(month==10||month==8||month==7||month==5||month==3||month==1)
		{
		date=year+"-"+(month)+"-"+(31-(7-day));
		}
		//否则就是3月1日
		//判断是否闰年
		else if((year%4==0&&year%100!=0)||year%400==0)
		{
		date=year+"-"+(month)+"-"+(29-(7-day));
		}
		else{
		date=year+"-"+(month)+"-"+(28-(7-day));
		}
		}
		}	//否则不是当月前天
		else{
		date=year+"-"+(month+1)+"-"+(day-7); 
		}
	    return date;
	}
	
	public static String getTimeStr(){
		int y,m,d,h,mi,s,ms;
		Calendar cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH)+1; 
		d=cal.get(Calendar.DATE); 
		h=cal.get(Calendar.HOUR_OF_DAY); 
		mi=cal.get(Calendar.MINUTE); 
		s=cal.get(Calendar.SECOND); 
		ms=cal.get(Calendar.MILLISECOND);
		String yStr=y<10?("0"+y):String.valueOf(y);
		String mStr=m<10?("0"+m):String.valueOf(m);
		String dStr=d<10?("0"+d):String.valueOf(d);
		String hStr=h<10?("0"+h):String.valueOf(h);
		String miStr=mi<10?("0"+mi):String.valueOf(mi);
		String sStr=s<10?("0"+s):String.valueOf(s);
		String msStr=ms<10?("0"+ms):String.valueOf(ms);
		String time=yStr+mStr+dStr+hStr+miStr+sStr+msStr;
		return time;
	}
	
	public static String getTimeStr(Date time){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String timeStr = "";
		if(time != null){
			timeStr = format.format(time);
		}
		return timeStr;
	}
	
	public static String getMonitorTimeStr(Date time){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr="";
		if(time != null){
			timeStr = format.format(time);
		}
		return timeStr;
	}	
	
	public static Date getDateTime(){
		return getDate(getDataTimeStr());
	}
	
	public static String getDataTimeStr(){
		int y,m,d,h,mi,s;
		Calendar cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH)+1; 
		d=cal.get(Calendar.DATE); 
		h=cal.get(Calendar.HOUR_OF_DAY); 
		mi=cal.get(Calendar.MINUTE); 
		s=cal.get(Calendar.SECOND); 
		String yStr=y<10?("0"+y):String.valueOf(y);
		String mStr=m<10?("0"+m):String.valueOf(m);
		String dStr=d<10?("0"+d):String.valueOf(d);
		String hStr=h<10?("0"+h):String.valueOf(h);
		String miStr=mi<10?("0"+mi):String.valueOf(mi);
		String sStr=s<10?("0"+s):String.valueOf(s);
		String time=yStr+"-"+mStr+"-"+dStr+" "+hStr+":"+miStr+":"+sStr;
		return time;
	}
	
	public static String getfileNameTime(){
		int y,m,d,h,mi,s;
		Calendar cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH)+1; 
		d=cal.get(Calendar.DATE); 
		h=cal.get(Calendar.HOUR_OF_DAY); 
		mi=cal.get(Calendar.MINUTE); 
		s=cal.get(Calendar.SECOND); 
		String yStr=y<10?("0"+y):String.valueOf(y);
		String mStr=m<10?("0"+m):String.valueOf(m);
		String dStr=d<10?("0"+d):String.valueOf(d);
		String hStr=h<10?("0"+h):String.valueOf(h);
		String miStr=mi<10?("0"+mi):String.valueOf(mi);
		String sStr=s<10?("0"+s):String.valueOf(s);
		String time=yStr+"-"+mStr+"-"+dStr+"-"+hStr+"-"+miStr+"-"+sStr;
		return time;
	}
	
	public static Long getTimeInMillis(){
		Calendar cal=Calendar.getInstance(); 
		return cal.getTimeInMillis();
	}
	
	public static boolean judgmentlate(String provisionsTime){
		DateFormat dateFormat=new SimpleDateFormat("hh:mm");
		Calendar c1=Calendar.getInstance();
		String nowtime=getTimeStr1();
		Calendar c2=Calendar.getInstance();
		try {
			c1.setTime(dateFormat.parse(nowtime));
			c2.setTime(dateFormat.parse(provisionsTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c1.compareTo(c2)>0;
	}
	
	
	public static int getMonth(String dateTime){
		dateTime = dateTime.substring(0,10);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(dateTime));
			return c.get(Calendar.MONTH)+1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getYear(String dateTime){
		dateTime = dateTime.substring(0,10);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(dateTime));
			return c.get(Calendar.YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		String ss = CalendarHelp.getDataTimeStr();
		System.out.println(ss);
	}
	
	/**
	 * 将字符串的时间转换为Date对象
	 * @param dateString	时间字符串
	 * @return				对应Date对象
	 */
	public static Date getDate(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if(dateString != null && !dateString.equals("null")){
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		return date;
	}
}
