package week02;

import java.util.ArrayList;

public class Polynom {
	ArrayList<Integer> coefficients;
	
	public Polynom() {
		coefficients = new ArrayList<Integer>();
	}
	
	private Polynom(ArrayList<Integer> list) {
		this.set(list);
	}
	
	public Polynom(String expression) {
		try {
			fromString(expression);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Polynom getFirstDerivative() {
		ArrayList<Integer> coefExpo = new ArrayList<>();
		int startIndex = 1;
		for (int i = startIndex; i < this.coefficients.size(); i++) {
			Integer coef = this.coefficients.get(i);
			coefExpo.add(coef * i);
		}
		return new Polynom(coefExpo);
	}
	
	public int evaluate(int x) {
		int result = 0;
		for (int i = 0; i < coefficients.size(); i++) {
			Integer coef = this.coefficients.get(i);
			int xPowered = 1;
			for (int j = 0; j < i; j++) {
				xPowered = xPowered * x;
			}
			result += coef * xPowered;
		}
		return result;
	}
	
	public Polynom add(int exponent, int coef) {
		Polynom resultP = new Polynom();
		ArrayList<Integer> resultPCoefExpo = new ArrayList<Integer>();
		for (int i = 0; i <= exponent; i++) {
				if(i < coefficients.size()) {
					resultPCoefExpo.add(coefficients.get(i));
				} else {
					if(i < exponent) {
						resultPCoefExpo.add(0);
					} else {
						resultPCoefExpo.add(coef);
					}
				}
		}
		resultP.set(resultPCoefExpo);
		return resultP;
	}
	
	public Polynom add(Polynom p) {
		return doOperation(p, '+');
	}
	
	public Polynom substract(Polynom p) {
		return doOperation(p, '-');
	}
	
//	public Polynom multiply(Polynom p) {
//		return doOperation(p, '*');
//	}
	
	public Polynom multiply(int constant) {
		Polynom resultP = new Polynom();
		ArrayList<Integer> resultPCoefExpo = new ArrayList<Integer>();
		for (int i = 0; i < coefficients.size(); i++) {
			Integer coef = coefficients.get(i);
			resultPCoefExpo.add(coef * constant);
		}
		resultP.set(resultPCoefExpo);
		return resultP;
	}
	
	private Polynom doOperation(Polynom p, char operation) {
		Polynom resultP = new Polynom();
		ArrayList<Integer> resultPCoefficients = new ArrayList<Integer>();
		ArrayList<Integer> pCoefExpo = p.get();
		if(p.get().size() > coefficients.size()) {
			for (int i = 0; i < pCoefExpo.size(); i++) {
				Integer coef;
				if(i < coefficients.size()) {
					coef = coefficients.get(i);
				} else {
					coef = 0;
				}
				Integer coefP = pCoefExpo.get(i);
				addResult(resultPCoefficients, coef, coefP, operation);
			}
		} else {
			for (int i = 0; i < coefficients.size(); i++) {
				Integer coef = coefficients.get(i);
				Integer coefP;
				if(i < pCoefExpo.size()) {
					coefP = pCoefExpo.get(i);
				} else {
					coefP = 0;
				}
				addResult(resultPCoefficients, coef, coefP, operation);
			}
		}
		resultP.set(resultPCoefficients);
		return resultP;
	}

	private void addResult(ArrayList<Integer> resultPCoefficients, Integer coef, 
			Integer coefP, char operation) {
		switch (operation) {
		case '+':
			resultPCoefficients.add(coef + coefP);
			break;
		case '-':
			resultPCoefficients.add(coef - coefP);
			break;
		case '*':
			resultPCoefficients.add(coef * coefP);
			break;
		}
	}

	public void set(ArrayList<Integer> coefExpo) {
		this.coefficients = coefExpo;
	}

	private ArrayList<Integer> get() {
		return this.coefficients;
	}

	// expected expression input in form "2x^4 + 3x^2 - 10x + 3"
	// with space between each member/operation
	private void fromString(String expression) {
		if(!validExpression(expression))
			throw new IllegalArgumentException("Error! Expression not in correct format (\"-2x^4 + 3x^2 - 10x + 3\" with whitespaces!)");
		
		coefficients = new ArrayList<Integer>();
		String[] expressionComponents = expression.split(" ");
		for (int i = 0; i < expressionComponents.length; i+=2) {
			boolean isPositive = isComponentSignPositive(expressionComponents, i);
			addComponent(expressionComponents[i], isPositive);
		}
	}

	private boolean isComponentSignPositive(String[] expressionComponents, 
			int index) {
		if(index == 0) {
			return true;
		} else {
			String sign = expressionComponents[index - 1];
			if(sign.equals("-"))
				return false;
			else
				return true;
		}
	}

	private void addComponent(String component, boolean sign) {
		Integer coefficient = getComponentCoefficient(component);
		int exponent = getComponentExponent(component);
		addComponentInCorrectSpot(coefficient, exponent, sign);
	}

	private void addComponentInCorrectSpot(Integer coefficient, int exponent, boolean isPositive) {
		int startIndex = 0;
		while(startIndex <= exponent) {
			if(coefficients.size() == startIndex) {
				coefficients.add(0);
			}
			startIndex++;
		}
		if(isPositive)
			coefficients.set(exponent, coefficient); 
		else 
			coefficients.set(exponent, -1 * coefficient);
	}

	// component expected as "nx^m", no energy for validation here though.. :D
	private Integer getComponentCoefficient(String component) {
		if(!component.contains("x")) 
			return Integer.parseInt(component);
		else 
			return Integer.parseInt(component.split("x")[0]);
	}

	// same here, component expected as "nx^m" ...
	private int getComponentExponent(String component) {
		if(!component.contains("x")) 
			return 0;
		else {
			if(!component.contains("^"))
				return 1;
			else {
				String[] temp = component.split("x|\\^");
				return Integer.parseInt(temp[temp.length - 1]);
			}
		}
	}

	// simple validation, looks for pattern in array: 
	// [member],[operation],[member],[operation]...
	private boolean validExpression(String expression) {
		String[] expressionComponents = expression.split(" ");
		for (int i = 0; i < expressionComponents.length; i++) {
			String exprMem = expressionComponents[i];
			if(i % 2 == 0) {
				if(exprMem.matches(".*\\d.*"))
					continue;
				else
					return false;
			} else {
				if(exprMem.equals("+") || exprMem.equals("-"))
					continue;
				else
					return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String output = "";
		for (int i = coefficients.size() - 1; i >= 0; i--) {
			if(coefficients.get(i) >= 0) 
				output+= "+" + coefficients.get(i) + "x^" + i;
			 else 
				output+= coefficients.get(i) + "x^" + i;
		}
		return output;
	}
}
