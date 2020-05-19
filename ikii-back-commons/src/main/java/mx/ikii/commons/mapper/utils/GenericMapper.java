package mx.ikii.commons.mapper.utils;

import java.util.List;

/**
 * This interface is used to provide a generic way of managing the Entity,
 * Request and Response of the resource along with mapstruct
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 * @param <E>  Entity
 * @param <RQ> Request
 * @param <RS> Response
 */
public interface GenericMapper<E, RQ, RS> {

	RQ entityToRequest(E entity);

	E requestToEntity(RQ request);

	List<E> requestToEntity(List<RQ> request);

	E responseToEntity(RS response);

	List<E> responseToEntity(List<RS> responses);

	RS entityToResponse(E entity);

	List<RS> entityToResponse(List<E> entities);

}
