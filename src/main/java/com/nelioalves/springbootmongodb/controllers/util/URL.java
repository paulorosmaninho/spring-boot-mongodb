package com.nelioalves.springbootmongodb.controllers.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	// Método estático para decodificar a URL recebida com
	// caracteres especiais.
	// Exemplo: Bom%20dia

	public static String decodeParam(String text) {

		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}

	}

	//Converte data recebida como parâmetro via URL
	public static Date convertDate(String textDate, Date defaultValue) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}

	}

}
