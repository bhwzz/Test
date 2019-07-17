package client;

abstract public class Tool { // ������һЩ����
	public static boolean isChineseCharacters(String name) { // �ж��ַ����Ƿ�ȫΪ����
		for (int i = 0; i < name.length(); i++) {
			if (!(19968 <= (int) name.charAt(i) && (int) name.charAt(i) < 40869)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumber(String str) { // �ж��ַ����Ƿ�ȫΪ����
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
