import java.util.*;
import java.net.*;
import java.io.*;

public class Client{

	public static void main(String[] args)throws Exception{
		Socket soc = new Socket("192.168.1.8",5000);
		OutputStream out = soc.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeBytes("xyz");
		System.out.println("data sent");
		dos.close();
		out.close();
		soc.close();
	}


}
