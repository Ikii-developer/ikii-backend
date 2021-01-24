package mx.ikii.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.ikii.business.repository.IHelpBusinessRepository;
import mx.ikii.commons.mapper.business.IJoinUsMapper;
import mx.ikii.commons.payload.request.general.JoinUsRequest;

@Service
public class HelpBusinessServiceImpl implements IHelpBusinessService {
  
  @Autowired
  private IHelpBusinessRepository helpBusinessRepository;
  
  @Autowired
  private IJoinUsMapper joinUsMapper;

  @Override
  public void joinUs(JoinUsRequest joinUsRequest) {
    helpBusinessRepository.save(joinUsMapper.requestToEntity(joinUsRequest));
  }
  
  

}
