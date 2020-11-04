package mx.ikii.web.commons.repository;

import java.util.Map;

public interface IGenericIkiiRepository {
  
  void updateDocumentFields(String id, Map<String, Object> fields, String collectionName);

}
