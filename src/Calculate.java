import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculate {
	final static String ERROR_MESSAGE = "Error.";
	
	// 四則演算メソッド
	private static String calclateWithArithmeticOperation(String snum1, String snum2, String symbol) {
		BigDecimal num1 = new BigDecimal(0);
		BigDecimal num2 = new BigDecimal(0);
		BigDecimal ans = new BigDecimal(0);
		try {
			num1 = new BigDecimal(snum1);
			num2 = new BigDecimal(snum2);
		} catch (Exception e) {
			System.out.println(e);
		}
		switch (symbol) {
		case "×":
			ans = num1.multiply(num2);;
			break;
		case "÷":
			try {
				ans = num1.divide(num2);
			} catch (ArithmeticException e) {
				try {
					// 無限小数の場合は小数点第十一位で四捨五入
					ans = num1.divide(num2, 10, BigDecimal.ROUND_HALF_UP);
				} catch (ArithmeticException e2) {
					// 分母 0 などのエラーハンドリング
					System.out.println(e2);
					return null;
				}
			}
			break;
		case "+":
			ans = num1.add(num2);
			break;
		case "-":
			ans = num1.subtract(num2);;
			break;
		}
		return ans.toString();
	}
	
	// 数値と記号に分けるメソッド。小数点は数値と分類。
	private static String[] separate(String[] fArray) {
		String[] sArray = new String[0];
		
		int cnt = 0;
		
		boolean isSymbol = false;
		boolean isPoint = false;
		
		for (int i = 0; i < fArray.length; i++) {
			try {
				Integer.parseInt(fArray[i]);
				if (isSymbol) {
					cnt++;
					isSymbol = false;
				}
			} catch (Exception e) {
				if (i <= 0 || i >= fArray.length-1) {
					System.out.println("記号(+-*/.)がおかしなところにいるのでエラー: " + i);
					return null;
				}
				if (isSymbol) {
					System.out.println("記号(+-*/)が連続しているのでエラー: " + i);
					return null;
				}
				switch (fArray[i]) {
				case "×":
				case "÷":
				case "+":
				case "-":
					cnt++;
					isSymbol = true;
					if (isPoint) isPoint = false;
					break;
				case ".":
					if (isPoint) {
						System.out.println("記号(.)が連続しているのでエラー: " + i);
						return null;
					}
					else isPoint = true;
					break;
				default:
					System.out.println("英字等はエラー: " + i);
					return null;
				}
			}
			
			try {
				// 要素が存在すれば結合
				sArray[cnt] += fArray[i];
			} catch (ArrayIndexOutOfBoundsException e) {
				// 要素が存在しなければ作成
				ArrayList<String> a = new ArrayList<String>(Arrays.asList(sArray));
				a.add(fArray[i]);
				sArray = a.toArray(new String[0]);
			}

			
		}
		
		return sArray;
	}
	
	// 乗除の演算子を判別するメソッド
	private static boolean isMultiplicationOrDivisionSymbol(String symbol) {
		if (symbol.equals("×") || symbol.equals("÷")) return true;
		else return false;
	}
	
	// 指定の配列の要素同士を計算して返すメソッド
	private static String[] calculateArrayElements(String[] sArray, int n) {
		String ans = calclateWithArithmeticOperation(sArray[n-1], sArray[n+1], sArray[n]);
		if (ans == null) return null;
		sArray[n-1] = ans;
		
		for (int j = n; j < sArray.length-2; j++) {
			sArray[j] = sArray[j+2];
		}
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(sArray));
		a.remove(sArray.length-1);
		a.remove(sArray.length-2);
		sArray = a.toArray(new String[0]);
		
		return sArray;
	}
	
	
	/**
	 * @param formula
	 * @return
	 */
	static String calculate(String formula) {
		
		String[] sArray = new String[0];
		String[] fArray = formula.split("");
		
		if (formula.length() == 0) {
			System.out.println("数値が入力されていません。");
			return ERROR_MESSAGE;
		}
		
		sArray = separate(fArray);
		if (sArray == null) return ERROR_MESSAGE;
		else if (sArray.length == 1) return sArray[0];
		
		for (int i = 0; i < sArray.length; i++) {
			if (isMultiplicationOrDivisionSymbol(sArray[i])) {
				sArray = calculateArrayElements(sArray, i);
				if (sArray == null) return ERROR_MESSAGE;
				
				i = 0;
			}
		}
		
		while (sArray.length > 1) {
			sArray = calculateArrayElements(sArray, 1);
			if (sArray == null) return ERROR_MESSAGE;
		}
		
		return sArray[0];
	}
}
