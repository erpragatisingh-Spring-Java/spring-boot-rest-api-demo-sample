package com.example.demo.util;

import java.util.UUID;

public class Utility {
	
	public static String getRandomUserId() {
		 
		return UUID.randomUUID().toString();
	}

}
