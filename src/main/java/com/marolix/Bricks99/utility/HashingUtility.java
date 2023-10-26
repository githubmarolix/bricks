package com.marolix.Bricks99.utility;

import java.util.Scanner;

public class HashingUtility {
	public static String hashedString(String str) {
		String reverseString = "";
		String hashedString = "";

		for (int i = str.length() - 1; i >= 0; i--) {

			reverseString += str.charAt(i);
		}
		for (int i = 0; i < reverseString.length(); i++) {

			Character c = reverseString.charAt(i);
			if (Character.isDigit(c)) {
				hashedString += Integer.toHexString(c);
			} else if (Character.isLetter(c)) {
				hashedString += Integer.toOctalString(c);
			} else if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c))
				hashedString += Integer.toBinaryString(c);
		}
		if (hashedString.contains("0")) {
			hashedString = hashedString.replace("0", "k");
		} else if (hashedString.contains("1"))
			hashedString = hashedString.replace("1", "p");
		else if (hashedString.contains("2"))
			hashedString = hashedString.replace("2", "a");
		else if (hashedString.contains("3"))
			hashedString = hashedString.replace("3", "L");
		else if (hashedString.contains("4"))
			hashedString = hashedString.replace("4", "b");
		else if (hashedString.contains("7"))
			hashedString = hashedString.replace("7", "M");
		else if (hashedString.contains("5"))

			hashedString = hashedString.replace("5", "l2w");
		else if (hashedString.contains("9"))
			hashedString = hashedString.replace("9", "c");
		return hashedString.toLowerCase();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println("hashed password of String is \n" + s + "\n" + hashedString(s));

	}
}
