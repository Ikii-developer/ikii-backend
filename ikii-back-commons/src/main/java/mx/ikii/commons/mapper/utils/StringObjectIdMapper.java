package mx.ikii.commons.mapper.utils;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<String> asString(List<ObjectId> ids) {
		return ids.stream().map(id -> id.toHexString()).collect(Collectors.toList());
	}

}
