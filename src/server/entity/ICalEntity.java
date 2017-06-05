package server.entity;
import java.util.*;
import server.vo.*;

public interface ICalEntity {
	public List<String> doService(CalVO[] cals) throws Exception ;
	public String getMsgAddZeroException();
	public String getMsgSubZeroException();
	public String getMsgMulOneException();
	public String getMsgDivOneException();
}