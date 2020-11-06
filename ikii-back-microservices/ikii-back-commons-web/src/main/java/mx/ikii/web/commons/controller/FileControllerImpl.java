package mx.ikii.web.commons.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import mx.ikii.web.commons.service.IFileServiceWrapper;

@Component
public class FileControllerImpl implements IFileController {

  @Autowired
  private IFileServiceWrapper fileServiceWrapper;

  @Override
  public ResponseEntity<Void> uploadImage(@NotNull @RequestParam("image") MultipartFile image,
      @NotBlank @RequestParam("id") String id) {
    fileServiceWrapper.uploadImage(image, id);
    return ResponseEntity.ok().build();
  }

}
