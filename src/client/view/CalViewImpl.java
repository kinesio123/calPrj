package client.view;

import java.io.*;
import java.util.*;
import java.net.*;

public class CalViewImpl implements ICalView {
	
	private Properties serverInfo;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket s1;
			
	@Override
	public void doService() {			
		try {
			serverInfo = new Properties();
			BufferedReader exam = new BufferedReader(new FileReader("src\\client\\file\\input.txt"));
			String s;
			List<String> examList = new ArrayList<String>();
			try {
				s = exam.readLine();
				while (s!=null) {
					examList.add(s);
					s = exam.readLine();
				}
			} finally {
				exam.close();
			}
			serverInfo.loadFromXML(new FileInputStream("src\\client\\file\\information.xml"));
			
			try {										//네트워크 설정. Input = 가져오기 Output = 내보내기
				String serverAddress = serverInfo.getProperty("serverAddress");
				int portNumber = Integer.parseInt((String)(serverInfo.get("portNumber")));
				s1 = new Socket(serverAddress, portNumber);
			    out = new ObjectOutputStream(s1.getOutputStream());
				out.writeObject(examList);				// List 객체를 보내기 위해 ObjectOutputStream 사용.
				out.flush();							// Object를 통신하기 위해서는 Serializable 인터페이스상속이 필요.
				
				in = new ObjectInputStream(s1.getInputStream());
				List<String> resultList = (List<String>)in.readObject(); // controller에서 보낸 list를 받아오는 과정. 
		    	if (resultList.size() == examList.size()) {
		    		report(resultList);
		    	} else {
		    		errReport(resultList);
		    	}
			} catch (ConnectException connExc) {
				System.err.println("Could not connect the server.");
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (s1 != null && out == null && in == null) {
						s1.close();
					}
					if (s1 != null && out != null && in == null) {
						out.close();
						s1.close();
					} else if (s1 != null && out != null && in != null) {
						in.close();
						out.close();
						s1.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			System.out.println("지정된 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}
 	
	@Override
	public void report(List<String> resultList) {
		for (String str : resultList) {
			System.out.println(str);
		}
	}
	
	@Override
	public void errReport(List<String> resultList) {
		System.out.println(resultList.get(0));
	}
}

