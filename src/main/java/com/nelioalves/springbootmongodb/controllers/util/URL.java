package com.nelioalves.springbootmongodb.controllers.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	//Método estático para decodificar a URL recebida com 
	//caracteres especiais. 
	//Exemplo: Bom%20dia
	
	public static String decodeParam(String text) {
				
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}

}
