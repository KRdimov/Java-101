package week2;

public class FunctionCalls {
	public static int f1(int x) {
		return f2(x) + f3(x);
	}
	
	public static int f2(int x) {
		return 2 * x;
	}
	
	public static int f3(int x) {
		return x + 1;
	}
	
	public static int f4(int x) {
		return 2 * f1(x);
	}
}
