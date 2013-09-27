package guo.tool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.text.TabableView;

public class AutoSignService extends TimerTask {

	@Override
	public void run() {
		// 获取配置信息
		Properties pro = getProperty();
		this.doSign(pro);
	}

	/**
	 * 执行自动登录
	 * @param pro
	 */
	private void doSign(Properties pro) {
		String urlStr = pro.getProperty("sign.url");
		String frameUrl = pro.getProperty("sign.frame.url");
		String user = pro.getProperty("sign.username");
		String pwd = pro.getProperty("sign.password");
		String postKV = "username="+user+"&password="+pwd;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			
			OutputStream out = conn.getOutputStream();
			out.write(postKV.getBytes());
			out.flush();
			
			String session_value=conn.getHeaderField("Set-Cookie");
			System.out.println("---Session value : "+session_value);
			String sessionKV = session_value.split(";")[0];
			System.out.println("---Session kv : "+sessionKV);
			
			this.doGetResultRequest(frameUrl,sessionKV);
			
			int status = conn.getResponseCode();
			conn.disconnect();
			System.out.println("---AutoSign Executed // Username:"+user+" Password:"+pwd+" Response:"+status+" // "+CalendarHelp.getDataTimeStr());
			System.out.println(":)");
			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取登录成功后，Frame加载结果项
	 * @throws Exception 
	 */
	private void doGetResultRequest(String frameUrl,String sessionKV) throws Exception {
		URL url = new URL(frameUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Cookie", sessionKV);
		InputStream in = conn.getInputStream();
		BufferedReader bf = new BufferedReader(new InputStreamReader(in,"gb2312"));
		String line = null;
		StringBuilder rs = new StringBuilder();
		while((line=bf.readLine())!=null){
			rs.append(line);
		}
		bf.close();
		in.close();
		String s = rs.toString().replace("\"", "\'");
		String tag = "签到时间为：";
		int index = s.indexOf(tag);
		if(index>=0){
			String out = s.substring(index,index+tag.length()+8);
			System.out.println("---System response : "+ out + " // "+CalendarHelp.getDataTimeStr());
		}else{
			System.out.println(s);
		}
		
	}

	/**
	 * 获取配置信息
	 * @return
	 */
	public static Properties getProperty() {
		try {
//			String filePath = "resources/application.properties";
//			System.out.println("---Config file: "+filePath);
//			File file = new File(filePath);
//			if(!file.exists()){
//				file.createNewFile();
//				System.out.println("---Create file: "+filePath);
//			}			
			InputStream in = AutoSignService.class.getResourceAsStream("/application.properties"); //new FileInputStream(file);
			Properties pro = new Properties();
			pro.load(in);
			return pro;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	public static void main(String[] args){
		new AutoSignService().run();
	}
	*/
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("-------------------------------------------");
			System.out.println("---GeoBeansAutoSign Fired");
			System.out.println("---Author: guohengxi.dennis@gmail.com");
			System.out.println("---Vsersion: final 0.1");
			System.out.println("-------------------------------------------");
			Properties pro = getProperty();
			String user = pro.getProperty("sign.username");
			System.out.println("---Username to sign : "+user+"  // "+CalendarHelp.getDataTimeStr());
			
			Timer timer = new Timer();
			
			Double min = 20 + Math.random()*10;//随机分钟数
			Double sec = Math.random()*10*6;//随机秒
			
			String timeStr = CalendarHelp.getNewTimeStr()+" 08:"+min.intValue()+":"+sec.intValue();
			Date firstTime = CalendarHelp.getDate(timeStr);
			
			//如果启动时，时间已经超过指定时间，则往后延一天
			if (new Date().after(firstTime)) {
				Calendar cal = new GregorianCalendar();
				cal.setTime(firstTime);
				cal.add(Calendar.DATE, 1);
				firstTime = cal.getTime();
				System.out.println("---Delay, the next time to sign at ： "+CalendarHelp.getMonitorTimeStr(firstTime)+"  // "+CalendarHelp.getDataTimeStr());
			}
			
			long period = 1000*60*60*24;//24小时
			timer.schedule(new AutoSignService(), firstTime, period);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
