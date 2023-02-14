package com.wck.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
 * author : 김한울, 왕종휘, 정기범
 * purpose : DB 상의 size 데이터 sorting
 * 			 ex1) 8, 9, 10, 11, 12
 * 			 ex2) 44(55), 55(66), 80(70)
 * 			 ex3) XS, S, M, L, XL
 */
public class SizeSortUtil {
	static public Comparator<String> sizeSortComp = new Comparator<String>() {
        public int compare(String a, String b) {
           Map<String, Integer> map = new HashMap<>();
           map.put("XS", 1);
           map.put("S", 2);
           map.put("M", 3);
           map.put("L", 4);
           map.put("XL", 5);
           
           if(isNumber(a) & isNumber(b)) {
        	   return Integer.parseInt(a)-Integer.parseInt(b);
           }
           
           // 둘 다 숫자로 시작할 때 : 44 vs 80 / 44(55) vs 80 (70)
           if(isNumber(a.charAt(0)+"") & isNumber(b.charAt(0)+"")) return a.compareTo(b);
           else return map.get(a) - map.get(b);
        }
     };
     
     static private boolean isNumber(String str) {
 		boolean result = true;
 		// null, 공백일시
 		if (str == null || str.length() == 0) {
 			result = false;
 		}
 		// null이나 공백이 아닐시
 		else {
 			for (int i = 0; i < str.length(); i++) {
 				int c = (int) str.charAt(i);
 				// 숫자가 아니라면
 				if (c < 48 || c > 57) {
 					result = false;
 				}
 			}
 		}
 		return result;
 	}

}
