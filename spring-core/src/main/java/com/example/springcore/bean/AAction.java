package com.example.springcore.bean;

import com.example.springcore.service.BusinessService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("A")
@Component("action")
public final class AAction extends AbstractAction {

  public AAction(BusinessService businessService) {
    super(businessService);
  }

  @Override
  public String action() {

    boolean isService = getBusinessService().onService();

    if (isService) {
      return "[Action]:A, [Service]:Success";
    }

    return null;
  }
}
