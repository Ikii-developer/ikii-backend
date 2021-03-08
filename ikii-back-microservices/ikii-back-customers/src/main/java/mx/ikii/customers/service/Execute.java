package mx.ikii.customers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.customers.service.impl.ControlledCacheService;

@Slf4j
@Component
public class Execute implements CommandLineRunner {

  @Autowired
  ControlledCacheService controlledCacheService;

  @Override
  public void run(String... args) throws Exception {
    log.info("Ikii Customer App started!");
  }


}
