package server.service;
import java.util.*;

public class CalService {
	
	private ICalService ics;
	
	public CalService(ICalService ics) {
		this.ics = ics;
	}
	
	public List<String> doService(List list) throws Exception{
		return ics.doService(list);
	}
}
