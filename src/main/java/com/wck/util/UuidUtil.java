package com.wck.util;

import java.util.UUID;

public class UuidUtil {
	
	public static String generateUUID(int length) {
		return UUID.randomUUID().toString().substring(length+1);
	}

}
