package mx.ikii.commons.utils;

import java.math.BigDecimal;

public class BigDecimalHelper {
	
	private BigDecimalHelper() {}
	
	public static Integer bigDecimalToCents(BigDecimal toConvert) {
		return toConvert.multiply(BigDecimal.valueOf(100)).intValue();
	}

}
