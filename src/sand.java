

public class sand {
	static int calclateWithArithmeticOperation(String snum1, String snum2, String symbol) {
		int num1 = 0;
		int num2 = 0;
		int ans = 0;
		try {
			num1 = Integer.parseInt(snum1);
			num2 = Integer.parseInt(snum2);
		} catch (Exception e) {
			System.out.println(e);
		}
		switch (symbol) {
		case "*":
			ans = num1 * num2;
			break;
		case "/":
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
	static int calc(String formula) {
		boolean isSymbol = false;
		boolean isPoint = false;
		String[] sArray = new String[formula.length()];
		String[] fArray = formula.split("");
		int[] priorityKey = new int[1000];
		int cnt = 0;
		int PKcnt = 0;
		
		for (int i = 0; i < formula.length(); i++) {
			try {
				Integer.parseInt(fArray[i]);
				if (isSymbol) {
					cnt++;
					isSymbol = false;
				}
			} catch (Exception e) {
				if (i <= 0 || i >= formula.length()-1) System.out.println("記号(+-*/.)がおかしなところにいるのでエラー: " + i);
				if (isSymbol) System.out.println("記号(+-*/)が連続しているのでエラー: " + i);
				switch (fArray[i]) {
				case "*":
				case "/":
					priorityKey[PKcnt++] = cnt + 1;
				case "+":
				case "-":
					cnt++;
					isSymbol = true;
					if (isPoint) isPoint = false;
					break;
				case ".":
					if (isPoint) System.out.println("記号(.)が連続しているのでエラー: " + i);
					else isPoint = true;
					break;
				default:
					//  英字等はerror
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
			int ans = calclateWithArithmeticOperation(sArray[before],sArray[after], sArray[symbolKey]);
			// デバッグ用
			System.out.println("a[" + i + "]: " + ans);
			
			sArray[before] = Integer.toString(ans);
			
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
		
		return 1;
	}
	public static void main(String[] args) {
		String str = "12/4/3/1+5-99/3*2/6*3*4*2+55/5";
        System.out.println(str);
        System.out.println(calc(str));
    }
}
