package mx.ikii.commons.mapper.transaction;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.transaction.clip.crud.TransactionClipRequest;
import mx.ikii.commons.payload.response.transaction.TransactionClipResponse;
import mx.ikii.commons.persistence.collection.TransactionClip;

/**
 * This interface helps to map the DTO Transaction
 * resource(Entity,Request,Response)
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface ITransactionClipMapper
		extends GenericMapper<TransactionClip, TransactionClipRequest, TransactionClipResponse> {
}
