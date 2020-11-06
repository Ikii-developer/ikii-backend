package mx.ikii.web.commons.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.PutObjectResult;
import mx.ikii.web.commons.repository.aws.IAwsFileRepository;

@Service
public class FileServiceImpl implements IFileService {

  @Autowired
  private IAwsFileRepository awsFileRepository;

  @Value("${ikii.upload-ikii-folder}")
  private String UPLOAD_IKII_FOLDER;

  @Override
  public PutObjectResult uploadImage(MultipartFile image, String fileName) {

    Path path = Paths.get(UPLOAD_IKII_FOLDER).resolve(fileName).toAbsolutePath();
    Resource recurso = null;
    try {
      Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
      recurso = new UrlResource(path.toUri());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // awsFileRepository.deleteImage(recurso.getFilename());
    return awsFileRepository.uploadImage(recurso);

  }

  @Override
  public void deleteImage(String fileName) {
    try {

      Path path = Paths.get(UPLOAD_IKII_FOLDER).resolve(fileName).toAbsolutePath();
      Files.deleteIfExists(path);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
