

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
	/**
	 * @param formula
	 * @return
	 */
	static String calc(String formula) {
		final String ERROR_MESSAGE = "Error.";
		
		boolean isSymbol = false;
		boolean isPoint = false;
		String[] sArray = new String[formula.length()];
		String[] fArray = formula.split("");
		int[] priorityKey = new int[1000];
		int cnt = 0;
		int PKcnt = 0;
		int SYcnt = 0;
		
		if (formula.length() == 0) {
			System.out.println("数値が入力されていません。");
			return ERROR_MESSAGE;
		}
		
		// 数値と記号に分ける。小数点は数値と分類。
		for (int i = 0; i < formula.length(); i++) {
			try {
				Integer.parseInt(fArray[i]);
				if (isSymbol) {
					cnt++;
					isSymbol = false;
				}
			} catch (Exception e) {
				if (i <= 0 || i >= formula.length()-1) {
					System.out.println("記号(+-*/.)がおかしなところにいるのでエラー: " + i);
					return ERROR_MESSAGE;
				}
				if (isSymbol) {
					System.out.println("記号(+-*/)が連続しているのでエラー: " + i);
					return ERROR_MESSAGE;
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
						return ERROR_MESSAGE;
					}
					else isPoint = true;
					break;
				default:
					System.out.println("英字等はエラー: " + i);
					return ERROR_MESSAGE;
				}
			}

			if (sArray[cnt] == null) sArray[cnt] = fArray[i];
			else sArray[cnt] += fArray[i];
		}
		
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
		
		return sArray[0];
	}
	public static void main(String[] args) {
		String str = "5.62 +1.38";
        System.out.println(str);
        System.out.println(calc(str));
    }
}
