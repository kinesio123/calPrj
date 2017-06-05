package server.entity;
import server.vo.*;
import java.util.*;

public class CalEntity {
	
	private ICalEntity ice;
	
	public CalEntity(ICalEntity ice) {
		this.ice = ice;
	}
	
	public List<String> doService(CalVO[] cals) throws Exception {
		return ice.doService(cals);
	}
	
	public String getMsgAddZeroException() {
		return ice.getMsgAddZeroException();
	}
	
	public String getMsgSubZeroException() {
		return ice.getMsgSubZeroException();
	}
	
	public String getMsgMulOneException() {
		return ice.getMsgMulOneException();
	}
	
	public String getMsgDivOneException() {
		return ice.getMsgDivOneException();
	}
}
