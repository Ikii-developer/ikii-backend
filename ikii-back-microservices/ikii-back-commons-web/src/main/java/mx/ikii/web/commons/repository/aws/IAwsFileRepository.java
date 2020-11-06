package mx.ikii.web.commons.repository.aws;

import org.springframework.core.io.Resource;
import com.amazonaws.services.s3.model.PutObjectResult;

public interface IAwsFileRepository {
  
  PutObjectResult uploadImage(Resource recurso);
  
  void deleteImage(String name);

}
