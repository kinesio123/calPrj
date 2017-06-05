package server.controller;

import server.vo.*;
import server.exception.*;
import server.service.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CalController {

	private CalService cs;
	private CalMsgVO mvo;
	private CalExcMsgVO emvo;
	private List list;
	private List<String> resultList;
	private Properties serverInfo;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public CalController(CalService cs, CalMsgVO mvo, CalExcMsgVO emvo) {
		this.cs = cs;
		this.mvo = mvo;
		this.emvo = emvo;
	}

	public void doService() {	
		serverInfo = new Properties();	
		ServerSocket s = null;
		try {
			serverInfo.loadFromXML(new FileInputStream("src\\server\\file\\information.xml"));
			int portNumber = Integer.parseInt((String)(serverInfo.get("portNumber")));
			s = new ServerSocket(portNumber);
			Socket s1 = s.accept();	
			while (true) {
				try {										
					in = new ObjectInputStream(s1.getInputStream());
					List<String> examList = (List<String>)in.readObject();	
					
					int size = examList.size();
					CalVO[] cals = new CalVO[size];
					for (int i=0; i<size; i++) {
						String opsString = (String)examList.get(i);
						String[] ops = opsString.split(" ");
						cals[i] = new CalVO(ops);
					}
					list = new ArrayList();
					list.add(cals);
					list.add(mvo);		
					list.add(emvo);	
					System.out.println("CalController:: Go AOP");							
					resultList = cs.doService(list);
					out = new ObjectOutputStream(s1.getOutputStream());
					out.writeObject(resultList);	
					out.flush();						
				} catch(IOException e) {
					resultList = new ArrayList<String>();
					out = new ObjectOutputStream(s1.getOutputStream());
					resultList.add(e.getMessage());
			    	out.writeObject(resultList);
				} finally {
					if (in != null) {			
			    		in.close();
			    	}
		    		if (out != null) {			
		    			out.close();
		    		} 
				}
			}
		} catch (Exception e) {
			System.out.println("지정된 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} 
	}
}
