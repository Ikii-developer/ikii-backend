package mx.ikii.web.commons.service;

import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.PutObjectResult;

public interface IFileService {

  PutObjectResult uploadImage(MultipartFile image, String fileName);
  
  void deleteImage(String fileName);
  
}
