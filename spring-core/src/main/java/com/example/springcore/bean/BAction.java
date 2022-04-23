package com.example.springcore.bean;

import com.example.springcore.service.BusinessService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("B")
@Component("action")
public final class BAction extends AbstractAction {

  BAction(BusinessService businessService) {
    super(businessService);
  }

  @Override
  public String action() {
    boolean isService = getBusinessService().onService();

    if (isService) {
      return "[Action]:B, [Service]:Success";
    }

    return null;
  }
}
