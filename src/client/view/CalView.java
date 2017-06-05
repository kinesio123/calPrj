package client.view;
import java.util.*;

public class CalView {
	
	private ICalView icv;
	
	public CalView(ICalView icv) {
		this.icv = icv; 
	}
	
	public void doService() {
		icv.doService();
	}
	
	public void report(List<String> resultList) {
		icv.report(resultList);
	}
	
	public void errReport(List<String> resultList) {
		icv.errReport(resultList);
	}
}
