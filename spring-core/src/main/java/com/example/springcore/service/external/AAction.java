package com.example.springcore.service.external;

import com.example.springcore.service.BusinessService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("A")
@Component("action")
public final class AAction extends AbstractExternalAction {

  public AAction(BusinessService businessService) {
    super(businessService);
  }

  @Override
  public String action() {

    boolean isService = getBusinessService().onService();

    this.created();

    this.success();

    this.update();

    this.match();

    if (isService) {
      return "[Action]:A, [Service]:Success";
    }

    return null;
  }
}
