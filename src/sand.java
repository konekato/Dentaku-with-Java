import java.util.ArrayList;
import java.util.Arrays;

public class sand {
	static double calclateWithArithmeticOperation(String snum1, String snum2, String symbol) {
		double num1 = 0;
		double num2 = 0;
		double ans = 0;
		try {
			num1 = Double.parseDouble(snum1);
			num2 = Double.parseDouble(snum2);
		} catch (Exception e) {
			System.out.println(e);
		}
		switch (symbol) {
		case "×":
			ans = num1 * num2;
			break;
		case "÷":
			ans = num1 / num2;
			break;
		case "+":
			ans = num1 + num2;
			break;
		case "-":
			ans = num1 - num2;
			break;
		}
		return ans;
	}
	
	// 数値と記号に分ける。小数点は数値と分類。
	static String[] separate(String[] fArray) {
		String[] sArray = new String[0];
		
		int cnt = 0;
		
		boolean isSymbol = false;
		boolean isPoint = false;
		
		for (int i = 0; i < fArray.length; i++) {
			System.out.println("cnt: " + cnt);
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
				sArray[cnt] += fArray[i];
			} catch (ArrayIndexOutOfBoundsException e) {
				ArrayList<String> a = new ArrayList<String>(Arrays.asList(sArray));
				a.add(fArray[i]);
				sArray = a.toArray(new String[0]);
			}

			
		}
		
		return sArray;
	}
	
	static boolean isMultiplicationOrDivisionSymbol(String symbol) {
		if (symbol.equals("×") || symbol.equals("÷")) return true;
		else return false;
	}
	
	
	/**
	 * @param formula
	 * @return
	 */
	static String calc(String formula) {
		final String ERROR_MESSAGE = "Error.";
		
		String[] sArray = new String[0];
		String[] fArray = formula.split("");
		
		if (formula.length() == 0) {
			System.out.println("数値が入力されていません。");
			return ERROR_MESSAGE;
		}
		
		sArray = separate(fArray);
		System.out.println(sArray.length);
		if (sArray == null) return ERROR_MESSAGE;
		else if (sArray.length == 1) return sArray[0];
		
		
		// debug
		System.out.println("First");
		for (int j = 0; j < sArray.length; j++) {
			System.out.println("sArray[" + j + "]: " + sArray[j]);
		}
		
		for (int i = 0; i < sArray.length; i++) {
			if (isMultiplicationOrDivisionSymbol(sArray[i])) {
				System.out.println("before, after: " + (i-1) + " " + (i+1));
				double ans = calclateWithArithmeticOperation(sArray[i-1],sArray[i+1], sArray[i]);
				// デバッグ用
				System.out.println("a[" + i + "]: " + ans);
				
				sArray[i-1] = Double.toString(ans);
				
				for (int j = i; j < sArray.length-2; j++) {
					sArray[j] = sArray[j+2];
				}
				ArrayList<String> a = new ArrayList<String>(Arrays.asList(sArray));
				a.remove(sArray.length-1);
				a.remove(sArray.length-2);
				sArray = a.toArray(new String[0]);
				
				for (int j = 0; j < sArray.length; j++) {
					System.out.println("sArray[" + j + "]: " + sArray[j]);
				}
				System.out.println();
				
				i = 0;
			}
		}

		System.out.println("--------------------");
		for (int j = 0; j < sArray.length; j++) {
			System.out.println("sArray[" + j + "]: " + sArray[j]);
		}
		System.out.println("--------------------");
		
		while (sArray.length > 1) {
			double ans = calclateWithArithmeticOperation(sArray[0], sArray[2], sArray[1]);
			sArray[0] = Double.toString(ans);
			for (int j = 1; j < sArray.length-2; j++) {
				sArray[j] = sArray[j+2];
			}
			
			ArrayList<String> a = new ArrayList<String>(Arrays.asList(sArray));
			a.remove(sArray.length-1);
			a.remove(sArray.length-2);
			sArray = a.toArray(new String[0]);
			
			for (int j = 0; j < sArray.length; j++) {
				System.out.println("sArray[" + j + "]: " + sArray[j]);
			}
			System.out.println();
		}
		
		if (sArray[0].endsWith(".0")) {
			sArray[0] = sArray[0].substring(0, sArray[0].length() - 2);
		}
		
		return sArray[0];
	}
	public static void main(String[] args) {
		String str = "3256.3+32×54";
        System.out.println(str);
        System.out.println(calc(str));
    }
}
