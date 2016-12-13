package week2;

import java.util.HashMap;
import java.util.Scanner;

public class FunctionCallsExtension {
	static int nFunctions;
	static String[] functions;
	static int x;
	static String expression;
	static HashMap<String, String[]> funcNameExpr;
	
	public static void readInput() {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		nFunctions = n;
		functions = new String[n];
		for (int i = 0; i < n; i++) {
			functions[i] = sc.nextLine();
		}
		fillfuncNameExpr();
		expression = sc.nextLine();
		x = Integer.parseInt(sc.nextLine());
	}

	public static void evaluate() {
		String[] functionsOrder = expression.split("\\.");
		reverse(functionsOrder);
		for (int i = 0; i < functionsOrder.length; i++) {
			String function = functionsOrder[i].trim();
			x = getFunctionResult(function, x);
		}
	}
	
	public static int getResult() {
		return x;
	}
	
	private static void fillfuncNameExpr() {
		funcNameExpr = new HashMap<>();
		for (int i = 0; i < functions.length; i++) {
			String[] breakdown = functions[i].split("=");
			String funcName = breakdown[0].trim().split(" ")[0];
			String[] funcExpr = breakdown[1].trim().split(" ");
			funcNameExpr.put(funcName, funcExpr);
		}
	}

	private static int getFunctionResult(String function, int x2) {
		String[] expression = funcNameExpr.get(function);
		int temp = 0;
		boolean isPlus = false;
		for (int i = 0; i < expression.length; i++) {
			String memberType = exprMemberType(expression[i]);
			String member = expression[i];
			switch (memberType) {
				case "argument":
					if(i == 0) 
						temp = x2;
					else {
						if(isPlus)
							temp += x2;
						else
							temp -= x2;
					}
				break;
				case "operator":
					if(member.equals("+"))
						isPlus = true;
					else
						isPlus = false;
				break;
				case "constant":
					if(i == 0) 
						temp = Integer.parseInt(member);
					else {
						if(isPlus)
							temp += Integer.parseInt(member);
						else
							temp -= Integer.parseInt(member);
					}
				break;
				case "function call":
					String innerFunction = member.substring(0, member.indexOf("("));
					if(i == 0) 
						temp = getFunctionResult(innerFunction, x2);
					else {
						int funcArg = getInnerFuncArgument(x2, member);
						if(isPlus)
							temp += getFunctionResult(innerFunction, funcArg);
						else
							temp -= getFunctionResult(innerFunction, funcArg);
					}
				break;
			}
		}
		return temp;
	}

	private static int getInnerFuncArgument(int x2, String member) {
		String argument = member.substring(member.indexOf('(') + 1, member.indexOf(')'));
		if(isNumeric(argument)) {
			return Integer.parseInt(argument);
		} else {
			return x2;
		}
	}

	private static String exprMemberType(String member) {
		if(member.indexOf('(') != -1) {
			return "function call";
		} else if(member.equals("+") || member.equals("-")) {
			return "operator";
		} else if(isNumeric(member)) {
			return "constant";
		} else {
			return "argument";
		}
	}

	private static void reverse(String[] functions) {
		for(int i = 0; i < functions.length / 2; i++)
		{
		    String temp = functions[i];
		    functions[i] = functions[functions.length - i - 1];
		    functions[functions.length - i - 1] = temp;
		}
	}
	
	private static boolean isNumeric(String str) {  
		try {  
			int m = Integer.parseInt(str);  
		} catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
}
