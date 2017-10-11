import java.util.*;

public class CronTabTime{
		
	private String min;
	private String hour;
	private String dayOfMonth;
	private String month;
	private String dayOfWeek;

	public void setCron(String[] time){
		this.min = time[0];
		this.hour = time[1];
		this.dayOfMonth = time[2];
		this.month = time[3];
		this.dayOfWeek = time[4];
	}

	public String getCron(int toggle){
		String cron = null;
		if(toggle == 1){
			 cron = min+" "+hour+" "+dayOfMonth+" "+month+" "+dayOfWeek+" python /home/pi/ProjectAlarm/sensoron.py";
		}
		return cron;
	}		
	

}