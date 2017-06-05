package server.vo;
import java.io.*;

public class CalExcMsgVO implements Serializable {
	private String msgAddZeroException;
	private String msgSubZeroException;
	private String msgMulOneException;
	private String msgDivOneException;
	private int idAddZeroException;
	private int idSubZeroException;
	private int idMulOneException;
	private int idDivOneException;
	
	public void setMsgAddZeroException(String msg) {
		this.msgAddZeroException = msg;
	}
	
	public String getMsgAddZeroException() {
		return msgAddZeroException;
	}
	
	public void setMsgSubZeroException(String msg) {
		this.msgSubZeroException = msg;
	}
	
	public String getMsgSubZeroException() {
		return msgSubZeroException;
	}
	
	public void setMsgMulOneException(String msg) {
		this.msgMulOneException = msg;
	}
	
	public String getMsgMulOneException() {
		return msgMulOneException;
	}
	
	public void setMsgDivOneException(String msg) {
		this.msgDivOneException = msg;
	}
	
	public String getMsgDivOneException() {
		return msgDivOneException;
	}

	public int getIdAddZeroException() {
		return idAddZeroException;
	}

	public void setIdAddZeroException(int idAddZeroException) {
		this.idAddZeroException = idAddZeroException;
	}

	public int getIdSubZeroException() {
		return idSubZeroException;
	}

	public void setIdSubZeroException(int idSubZeroException) {
		this.idSubZeroException = idSubZeroException;
	}

	public int getIdMulOneException() {
		return idMulOneException;
	}

	public void setIdMulOneException(int idMulOneException) {
		this.idMulOneException = idMulOneException;
	}

	public int getIdDivOneException() {
		return idDivOneException;
	}

	public void setIdDivOneException(int idDivOneException) {
		this.idDivOneException = idDivOneException;
	}
}
