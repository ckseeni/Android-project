import java.util.*;
import java.net.*;
import java.io.*;

public class Server extends BuzzarControl{

	public static void main(String[] args) throws Exception{
		ServerSocket serSoc = new ServerSocket(5000);
		BuzzarControl b1 = new BuzzarControl();
		System.out.println("Server ready");
		while(true){		
			Socket soc = serSoc.accept();
			InputStream input = soc.getInputStream();
			DataInputStream dis = new DataInputStream(input);
			String test = dis.readLine();
			System.out.println(test);
			
			if(test.equals("on"))
				b1.buzzarOn();
			else if(test.equals("off")){
				b1.buzzarOff();
			}else if(!test.equals("handshake_connection")){
				
				appendFun(test);
			}		
			dis.close();
			input.close();
			soc.close();
		}
		

	}

	public static void appendFun(String temp)throws Exception{
		
		String cron="";
		ArrayList<String> extractedTime = new ArrayList();
	
		String[] timeEntry = temp.split("\\s+");
		for(String x : timeEntry){
			String[] entry = x.split("\\-");
			for(String y : entry){
				cron = cron+" "+y;
			}
			extractedTime.add(cron+" * * * python /home/pi/ProjectAlarm/sensoron.py");
			cron="";
	
		}	
		extractedTime.add("@reboot sh /home/pi/ProjectAlarm/startupScript.sh");
		
		ListIterator li = extractedTime.listIterator();
		FileWriter writer = new FileWriter("cronFile.txt");
		while(li.hasNext()){
			temp = li.next().toString();
			System.out.println(temp);
			writer.write(temp);
			writer.write("\n");
		}
		writer.close();
		
		Runtime run = Runtime.getRuntime();
		run.exec("crontab cronFile.txt");


	}

	



}
