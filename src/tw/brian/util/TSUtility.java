package tw.brian.util;

import java.util.*;

/**
 * 
 * @Description TSUtility 用於互動介面
 * @author
 * @version
 * @date
 *
 */
public class TSUtility {
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * 輸入1-5
	 * 
	 * @return
	 */
	public static char readMenuSelection5() {
		char c;
		for (;;) {
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5') {
				System.out.print("選擇錯誤請重新輸入：");
			} else
				break;
		}
		return c;
	}

	/**
	 * 輸入1-3
	 * 
	 * @return
	 */
	public static char readMenuSelection3() {
		char c;
		for (;;) {
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if (c != '1' && c != '2' && c != '3') {
				System.out.print("選擇錯誤請重新輸入：");
			} else
				break;
		}
		return c;
	}

	/**
	 * 提示並等待
	 */
	public static void readReturn() {
		System.out.print("按ENTER繼續...");
		readKeyBoard(100, true);
	}

	
	/**
	 * 
	 * @return 4位整數
	 */
	public static int readInt() {
		int n;
		while(true) {
			String str = readKeyBoard(4, false);
			try {
				n = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				System.out.print("數字輸入錯誤，請重新輸入：");
			}
		}
		return n;
	}

	/**
	 * 讀取y/n
	 * 
	 * @return
	 */
	public static char readConfirmSelection() {
		char c;
		while (true) {
			String str = readKeyBoard(1, false).toUpperCase();
			c = str.charAt(0);
			if (c == 'Y' || c == 'N') {
				break;
			} else {
				System.out.print("選擇錯誤，請重新輸入：");
			}
		}
		return c;
	}
	
	
	public static String readDate() {
		String s="";
		while (true) {
			s = readKeyBoard(8, false);
			
			if (s.length() == 8) {
				break;
			} else {
				System.out.print("日期格式錯誤，請重新輸入：");
			}
		}
		return s;
	}
	
	
	
	/**
	 * 30字以下的字串，可回傳空白
	 * @return
	 */
	public static String readLine() {
		String s="";
		while (true) {
			s = readKeyBoard(30, false);
			
			if (s.length() > 0&&s.length()<30) {
				break;
			} else {
				System.out.print("格式錯誤，請重新輸入：");
			}
		}
		return s;
	}
	
	/**
	 * 專門用來讀path
	 * @return
	 */
	public static String readPath() {
		String s="";
		while (true) {
			s = readKeyBoard(100, false);
			
			if (s.length() > 0&&s.length()<100) {
				break;
			} else {
				System.out.print("格式錯誤，請重新輸入：");
			}
		}
		return s;
	}
	
	

	private static String readKeyBoard(int limit, boolean blankReturn) {
		String line = "";

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.length() == 0) {
				if (blankReturn)
					return line;
				else
					continue;
			}

			if (line.length() < 1 || line.length() > limit) {
				System.out.print("輸入長度大於" + limit + "，錯誤，請續輸入：");
				continue;
			}
			break;
		}

		return line;
	}
}
