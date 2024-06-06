package org.acme.utilities;

public class Identifier {

	public static String identifyContent(String input) {
		if(input.charAt(0)=='<') {
			return "xml";
		}
		else if(input.charAt(0)=='{') {
			return "json";
		}
		return "String";
	}
}
