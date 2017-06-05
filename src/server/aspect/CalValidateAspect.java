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
		System.out.println(cals.length+"개의 문제를 받았습니다.");
		for (int i=0; i<cals.length ; i++) {
			System.out.println("e"+(i+1)+"의 변수 검증 중입니다.");
			String Op1 = cals[i].getOp1();
			String Op = cals[i].getOp();
			String Op2 = cals[i].getOp2();
			
			if (isStringDouble(Op1)) {		//Op1 검증
				System.out.println("　Op1은 숫자입니다.");
			} else {
				throw new Exception("　Op1은 숫자가 아닙니다.");
			}
											//Op 검증
			if (Op.equalsIgnoreCase(CalVO.ADD) || Op.equalsIgnoreCase(CalVO.SUB) 
					||Op.equalsIgnoreCase(CalVO.MUL)|| Op.equalsIgnoreCase(CalVO.DIV)) {
				System.out.println("　Op는 연산자입니다.");
			} else {
				throw new Exception("　Op는 연산자가 아닙니다.");
			}
			
			if (isStringDouble(Op2)) {		//Op2 검증
				System.out.println("　Op2는 숫자입니다.");
			} else {
				throw new Exception("　Op2는 숫자가 아닙니다.");
			}
		}
		try {
			Object retValue = joinPoint.proceed();  //니가 왔던곳으로 가라 라는 명령 (core -> common으로 왔으니) 
			return retValue;						// 한번 왔던곳은 반드시 되돌아가야 하기때문에 위와같이 선언
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