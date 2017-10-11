public class BuzzarControl{

	private static Runtime run;

	public BuzzarControl(){
		run = Runtime.getRuntime();
	}


	public int buzzarOn(){
		try{
			run.exec("python /home/pi/ProjectAlarm/sensoron.py");
			return 1;
		}catch (Exception e){

			return 0;
		}
	}

	public int buzzarOff(){
		try{
			run.exec("python /home/pi/ProjectAlarm/sensoroff.py");
			return 1;
		}catch (Exception e){

			return 0;
		}
	}


}