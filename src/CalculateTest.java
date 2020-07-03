
public class CalculateTest {
	public static void main(String[] args) {
		String[][] testCases = {
				{"整数の加算", "1+2", "3"},
				{"整数の減算", "1-2", "-1"},
				{"整数の乗算", "1×2", "2"},
				{"整数の除算", "1÷2", "0.5"},
				{"小数の加算", "1.1+2.2", "3.3"},
				{"小数の減算", "1.1-2.2", "-1.1"},
				{"小数の乗算", "1.1×2.2", "2.42"},
				{"小数の除算", "1.1÷2.2", "0.5"},
				{"小数点が数字の最後なら整数扱い", "11.+2.2", "13.2"},
				{"初めに 0 がついている数の計算", "00001.1÷2.2", "0.5"},
				{"複数の演算1", "1.1+2.2×2.2÷92123-32×77", "-2462.8999474615"},
				{"複数の演算2", "2×3.141592×5×270÷360", "23.561940"},
				{"除算で分母が 0 (エラー)", "1.1÷0", Calculate.ERROR_MESSAGE},
				{"0/0 (エラー)", "0÷0", Calculate.ERROR_MESSAGE},
				{"大きな数", "423785712.324×334324123.112", "141681786660115591.632288"},
				{"記号(+-×÷)が連続(エラー)", "1÷-2", Calculate.ERROR_MESSAGE},
				{"記号(.)が連続1(エラー)", "1+2..52", Calculate.ERROR_MESSAGE},
				{"記号(.)が連続2(エラー)", "1+2.5.2", Calculate.ERROR_MESSAGE},
				{"初めに記号(+-×÷.)(エラー)", "+1÷2", Calculate.ERROR_MESSAGE},
				{"終わりに記号(+-×÷.)(エラー)", "1÷2+", Calculate.ERROR_MESSAGE},
				{"式に空白が入っている(エラー)", "1 ÷ 2", Calculate.ERROR_MESSAGE},
				{"式に記号(+-×÷.)以外の文字が入っている(エラー)", "x+2", Calculate.ERROR_MESSAGE},
				{"入力値が空(エラー)", "", Calculate.ERROR_MESSAGE},
		};
		int cnt = 0;
		
		System.out.println("Tests start.");
		for (int i = 0; i < testCases.length; i++) {
			System.out.println("Case" + (i+1) + " " + testCases[i][0] + ":");
			System.out.println("want " + testCases[i][1] + " = " + testCases[i][2]);
			String ans = Calculate.calc(testCases[i][1]);
			if (ans.equals(testCases[i][2])) {
				System.out.println(" -> 成功");
				cnt++;
			} else {
				System.out.println(" -> 失敗");
				System.out.println("because of " + ans);
			}
			System.out.println();
		}
		
		if (cnt == testCases.length) System.out.print("All clear! ");
		else System.out.print("Failed... ");
		System.out.println(cnt + "/" + testCases.length);
    }
}
