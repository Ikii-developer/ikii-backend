package mx.ikii.web.commons.repository.aws;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Repository
public class AwsFileRepositoryImpl implements IAwsFileRepository {

  @Autowired
  private AmazonS3 ikiiAwsS3client;

  @Value("${ikii.aws.s3.bucket-name}")
  private String bucketName;

  @Value("${ikii.aws.s3.upload-folder}")
  private String s3UploadFolder;

  @Override
  public PutObjectResult uploadImage(Resource recurso) {

    try {

      AmazonS3 s3Client = ikiiAwsS3client;

      PutObjectRequest request = new PutObjectRequest(bucketName,
          s3UploadFolder + recurso.getFilename(), recurso.getFile());

      // ObjectMetadata metadata = new ObjectMetadata();
      // metadata.setContentType("image/jpg");
      // metadata.addUserMetadata("id", id);
      // request.setMetadata(metadata);

      request.withCannedAcl(CannedAccessControlList.PublicRead);
      return s3Client.putObject(request);

    } catch (AmazonServiceException e) {
      e.printStackTrace();
    } catch (SdkClientException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void deleteImage(String name) {

    AmazonS3 s3Client = ikiiAwsS3client;
    s3Client.deleteObject(bucketName, s3UploadFolder + name);

  }

}
