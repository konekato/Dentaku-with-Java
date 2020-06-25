

public class sand {
	static int calc(String formula) {
		int isC = 0;
		String[] sArray = new String[formula.length()];
		String[] fArray = formula.split("");
		int cnt = 0;
		
		for (int i = 0; i < formula.length(); i++) {
			try {
				Integer.parseInt(fArray[i]);
			} catch (Exception e) {
				
				
				switch (fArray[i]) {
				case "+":
				case "-":
				case "*":
				case "/":
					cnt++;
					isC = 1;
					break;
				case ".":
					isC = 2;
					break;
				}
			}

			if (sArray[cnt] == null) sArray[cnt] =  fArray[i];
			else sArray[cnt] +=  fArray[i];
			
			if (isC == 1) {
				cnt++;
				isC = 0;
			}
			
		}
		for (int i = 0; i <= cnt; i++) {
			System.out.println("charArray[" + i + "]: " + sArray[i]);
		}
		
		return 1;
	}
	public static void main(String[] args) {
		String str = "1112132/4325.2222*436-2";
        System.out.println(str);
        System.out.println(calc(str));
    }
}
