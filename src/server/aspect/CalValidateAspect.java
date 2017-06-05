package server.aspect;

import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;

import server.vo.*;

public class CalValidateAspect {
	private List list;

	public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Let's begin validation.");
		Object[] obj = joinPoint.getArgs();
		list = (List) obj[0];
		CalVO[] cals = (CalVO[])list.get(0);
		System.out.println(cals.length+"���� ������ �޾ҽ��ϴ�.");
		for (int i=0; i<cals.length ; i++) {
			System.out.println("e"+(i+1)+"�� ���� ���� ���Դϴ�.");
			String Op1 = cals[i].getOp1();
			String Op = cals[i].getOp();
			String Op2 = cals[i].getOp2();
			
			if (isStringDouble(Op1)) {		//Op1 ����
				System.out.println("��Op1�� �����Դϴ�.");
			} else {
				throw new Exception("��Op1�� ���ڰ� �ƴմϴ�.");
			}
											//Op ����
			if (Op.equalsIgnoreCase(CalVO.ADD) || Op.equalsIgnoreCase(CalVO.SUB) 
					||Op.equalsIgnoreCase(CalVO.MUL)|| Op.equalsIgnoreCase(CalVO.DIV)) {
				System.out.println("��Op�� �������Դϴ�.");
			} else {
				throw new Exception("��Op�� �����ڰ� �ƴմϴ�.");
			}
			
			if (isStringDouble(Op2)) {		//Op2 ����
				System.out.println("��Op2�� �����Դϴ�.");
			} else {
				throw new Exception("��Op2�� ���ڰ� �ƴմϴ�.");
			}
		}
		try {
			Object retValue = joinPoint.proceed();  //�ϰ� �Դ������� ���� ��� ��� (core -> common���� ������) 
			return retValue;						// �ѹ� �Դ����� �ݵ�� �ǵ��ư��� �ϱ⶧���� ���Ͱ��� ����
		} catch (Throwable e) {
			throw e;
		} finally {
		}
	}
	
	private boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
		    return true;
		} catch (NumberFormatException e) {
		    return false;
		}
	}
}