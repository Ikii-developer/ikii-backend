package mx.ikii.commons.payload.dto;

import java.util.List;

import lombok.Data;

@Data
public class Location {

	private String type;
	
	private List<Double> coordinates;
	
//	@Data
//	public static class Coordinates {
//		
//		public Double longitude;
//		
//		public Double latitude;
//		
//	}
	
}