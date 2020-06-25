

public class sand {
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
				if (isSymbol) System.out.println("記号(+-*/)が連続しているのでエラー: " + i);
				switch (fArray[i]) {
				
				case "*":
				case "/":
					priorityKey[PKcnt++] = i;
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

			if (sArray[cnt] == null) sArray[cnt] =  fArray[i];
			else sArray[cnt] +=  fArray[i];
		}
		
		// デバッグ用
		for (int i = 0; i < PKcnt; i++) {
			System.out.println("isPI[" + i + "]: " + priorityKey[i]);
		}
		for (int i = 0; i <= cnt; i++) {
			System.out.println("charArray[" + i + "]: " + sArray[i]);
		}
		
		return 1;
	}
	public static void main(String[] args) {
		String str = "11.12132/43.25.2222*4.36+-2";
        System.out.println(str);
        System.out.println(calc(str));
    }
}
