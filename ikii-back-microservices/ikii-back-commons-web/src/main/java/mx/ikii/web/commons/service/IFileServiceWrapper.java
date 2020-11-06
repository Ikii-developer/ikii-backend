package mx.ikii.web.commons.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileServiceWrapper {

  void uploadImage(MultipartFile image, String id);
  
}
