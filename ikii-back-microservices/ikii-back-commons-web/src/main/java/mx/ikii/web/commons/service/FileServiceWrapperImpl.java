package mx.ikii.web.commons.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import mx.ikii.web.commons.repository.IGenericIkiiRepository;
import mx.ikii.web.commons.util.UpdateType;

@Service
public class FileServiceWrapperImpl implements IFileServiceWrapper {

  @Autowired
  private IFileService fileService;

  @Autowired
  private IGenericIkiiRepository genericIkiiRepository;
  
  @Value("${ikii.url-bucket}")
  private String urlBucketS3;
  
  @Value("${spring.application.name}")
  private String appName;

  @Override
  public void uploadImage(MultipartFile image, String id) {

    Map<String, Object> attributesToUpdate = new HashMap<>();
    String collectionName = null;
    
    StringBuilder fileName = new StringBuilder(id).append(".").append(image.getContentType().split("/")[1]);

    if (appName.equals(UpdateType.BUSINESS.toString().toLowerCase())) {
      
      attributesToUpdate.put("image", urlBucketS3+fileName.toString());
      collectionName = UpdateType.BUSINESS.getName();
      
    } else if (appName.equals(UpdateType.PRODUCTS.toString().toLowerCase())) {
      
      attributesToUpdate.put("pathImage", urlBucketS3+fileName.toString());
      collectionName = UpdateType.PRODUCTS.getName();
      
    } else if(appName.equals(UpdateType.CUSTOMER.toString().toLowerCase())) {
    	
    	attributesToUpdate.put("image", urlBucketS3+fileName.toString());
        collectionName = UpdateType.CUSTOMER.getName();
    	
    }
    
    fileService.uploadImage(image, fileName.toString());

    genericIkiiRepository.updateDocumentFields(id, attributesToUpdate, collectionName);

    fileService.deleteImage(fileName.toString());
  }

}
