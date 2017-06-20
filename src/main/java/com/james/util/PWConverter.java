package com.james.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 
 * @author James sung
 *
 */

@Converter
public class PWConverter implements AttributeConverter<String, String>{

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return AESCryptor.getInstance().encrypt(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return AESCryptor.getInstance().decrypt(dbData);
	}

}
