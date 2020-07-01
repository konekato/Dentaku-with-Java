

public class sand {
	static int[] priorityKey = new int[1000];
	static int cnt = 0;
	static int PKcnt = 0;
	static int SYcnt = 0;
	
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
		String[] sArray = new String[fArray.length];
		
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
					priorityKey[PKcnt++] = cnt + 1;
				case "+":
				case "-":
					cnt++;
					SYcnt++;
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

			if (sArray[cnt] == null) sArray[cnt] = fArray[i];
			else sArray[cnt] += fArray[i];
		}
		
		return sArray;
	}
	
	
	/**
	 * @param formula
	 * @return
	 */
	static String calc(String formula) {
		final String ERROR_MESSAGE = "Error.";
		
		String[] sArray = new String[formula.length()];
		String[] fArray = formula.split("");
		
		if (formula.length() == 0) {
			System.out.println("数値が入力されていません。");
			return ERROR_MESSAGE;
		}
		
		sArray = separate(fArray);
		if (sArray == null) return ERROR_MESSAGE;
		
		// debug
		System.out.println("First");
		for (int j = 0; j <= cnt; j++) {
			System.out.println("sArray[" + j + "]: " + sArray[j]);
		}
		System.out.println();
		
		for (int i = 0; i < PKcnt; i++) {
			int symbolKey = priorityKey[i];
			int before = symbolKey-1;
			int after = symbolKey+1;
			System.out.println("before, after: " + before + " " + after);
			double ans = calclateWithArithmeticOperation(sArray[before],sArray[after], sArray[symbolKey]);
			// デバッグ用
			System.out.println("a[" + i + "]: " + ans);
			
			sArray[before] = Double.toString(ans);
			
			for (int j = symbolKey; j <= cnt-2; j++) {
				sArray[j] = sArray[j+2];
			}
			sArray[cnt-1] = null;
			sArray[cnt] = null;
			for (int j = 0; j <= cnt; j++) {
				System.out.println("sArray[" + j + "]: " + sArray[j]);
			}
			System.out.println();
			cnt -= 2;
			if (i+1 < PKcnt) priorityKey[i+1] -= 2 * (i+1);
		}

		for (int j = 0; j <= cnt; j++) {
			System.out.println("sArray[" + j + "]: " + sArray[j]);
		}
		System.out.println();
		
		for (int i = 0; i < SYcnt - PKcnt; i++) {
			if (sArray[1] == null) break;
			double ans = calclateWithArithmeticOperation(sArray[0], sArray[2], sArray[1]);
			sArray[0] = Double.toString(ans);
			for (int j = 1; j <= cnt-2; j++) {
				sArray[j] = sArray[j+2];
			}
			sArray[cnt-1] = null;
			sArray[cnt] = null;
			for (int j = 0; j <= cnt; j++) {
				System.out.println("sArray[" + j + "]: " + sArray[j]);
			}
			System.out.println();
			cnt -= 2;
		}
		if (sArray[0].endsWith(".0")) {
			sArray[0] = sArray[0].substring(0, sArray[0].length() - 2);
		}
		
		return sArray[0];
	}
	public static void main(String[] args) {
		String str = "4÷2";
        System.out.println(str);
        System.out.println(calc(str));
    }
}
