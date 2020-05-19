package mx.ikii.commons.mapper.utils;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

/**
 * This class is used to have a correct conversion between objectId and String
 * in mapstruct
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
@Mapper(componentModel = "spring")
public class StringObjectIdMapper {
	public ObjectId asObjectId(String id) {
		return new ObjectId(id);
	}

	public String asString(ObjectId id) {
		return id.toHexString();
	}
}
