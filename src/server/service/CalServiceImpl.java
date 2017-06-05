package server.service;
import server.entity.*;
import server.exception.*;
import server.vo.*;
import java.util.*;

public class CalServiceImpl implements ICalService {

	private CalEntity ce;
	
	public CalServiceImpl(CalEntity ce) {
		this.ce = ce;
	}
	
	public List<String> doService(List list) throws Exception {
		System.out.println("CalService:: AOP °¬´Ù¿Ô´Ï");	
		CalVO[] cals = (CalVO[])list.get(0); 
		CalExcMsgVO emvo = (CalExcMsgVO)list.get(2);
		int result = 0;
		int size = cals.length;
		for (int i=0; i<size; i++) {
			int op1 = Integer.parseInt(cals[i].getOp1());
			String op = cals[i].getOp();
			int op2 = Integer.parseInt(cals[i].getOp2());
			if(op.equalsIgnoreCase(CalVO.ADD)) {
				if (op1 == 0 || op2 == 0) {
					emvo.setMsgAddZeroException(getMsgAddZeroException());
					throw new AddZeroException(emvo.getMsgAddZeroException());
				} else {
					result = op1+op2;
				}
			} else if (op.equalsIgnoreCase(CalVO.SUB)) {
				if (op2 == 0) {
					emvo.setMsgSubZeroException(getMsgSubZeroException());
					throw new SubZeroException(emvo.getMsgSubZeroException());
				} else {
					result = op1-op2;
				}
			} else if (op.equalsIgnoreCase(CalVO.MUL)) {
				if (op1 == 1 || op2 == 1) {
					emvo.setMsgMulOneException(getMsgMulOneException());
					throw new MulOneException(emvo.getMsgMulOneException());
				} else {
					result = op1*op2;
				}
			} else if (op.equalsIgnoreCase(CalVO.DIV)) {
				if (op2 == 1) {
					emvo.setMsgDivOneException(getMsgDivOneException());
					throw new DivOneException(emvo.getMsgDivOneException());
				} else {
					result = op1/op2;
				}
			}
			cals[i].setResult(result);
		}
		return ce.doService(cals);
	}
	
	public String getMsgAddZeroException() {
		return ce.getMsgAddZeroException();
	}
	
	public String getMsgSubZeroException() {
		return ce.getMsgSubZeroException();
	}
	
	public String getMsgMulOneException() {
		return ce.getMsgMulOneException();
	}
	
	public String getMsgDivOneException() {
		return ce.getMsgDivOneException();
	}
}