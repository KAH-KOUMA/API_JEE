package com.efficom.utils;

import java.sql.Timestamp;

public class Utils {
	
	//private static String insert = "INSERT INTO"
	public static Timestamp initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        return date;
    }
	
	public static String generateString(int n) {
		 String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
	        // create StringBuffer size of AlphaNumericString
	        StringBuilder sb = new StringBuilder(n);
	        for (int i = 0; i < n; i++) {
	            // generate a random number between
	            // 0 to AlphaNumericString variable length
	            int index = (int) (AlphaNumericString.length() * Math.random());
	            // add Character one by one in end of sb
	            sb.append(AlphaNumericString.charAt(index));
	        }
	        return sb.toString() + ".jpg";
	}
}
