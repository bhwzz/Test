package client;

abstract public class Tool { // 定义了一些方法
	public static boolean isChineseCharacters(String name) { // 判断字符串是否全为汉字
		for (int i = 0; i < name.length(); i++) {
			if (!(19968 <= (int) name.charAt(i) && (int) name.charAt(i) < 40869)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumber(String str) { // 判断字符串是否全为数字
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
