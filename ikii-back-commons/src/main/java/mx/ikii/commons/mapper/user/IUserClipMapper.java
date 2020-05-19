package mx.ikii.commons.mapper.user;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.payload.response.user.UserClipResponse;
import mx.ikii.commons.persistence.collection.UserClip;

/**
 * This interface helps to map the DTO User resource(Entity,Request,Response)
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IUserClipMapper extends GenericMapper<UserClip, UserClipRequest, UserClipResponse> {

	public UserClipRequest entityToRequest(UserClip userClip);
}
