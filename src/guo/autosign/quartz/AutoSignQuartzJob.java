package guo.autosign.quartz;

import guo.autosign.global.Global;
import guo.autosign.global.User;
import guo.autosign.global.UserDataBase;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoSignQuartzJob implements Job{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void execute(JobExecutionContext arg0)throws JobExecutionException {
		try {
			for(int i=0;i<UserDataBase.users.size();i++){
				User u = UserDataBase.users.get(i);
				this.connectToSignIn(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void connectToSignIn(User u){
		try {
			URL url = new URL(Global.SIGN_URL);
			HttpURLConnection conn = null;
			String param = "";
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			osw.write(param);
			osw.flush();
			osw.close();
			//读取返回内容
			StringBuffer buffer = new StringBuffer();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
			if (buffer.length()>0) {
				logger.debug("服务返回："+buffer.toString());
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
