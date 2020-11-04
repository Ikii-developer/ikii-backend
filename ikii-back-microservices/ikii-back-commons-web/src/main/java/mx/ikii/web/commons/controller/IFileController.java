package mx.ikii.web.commons.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public interface IFileController {
  
  @PostMapping
  ResponseEntity<Void> uploadImage(MultipartFile image, String id);

}
