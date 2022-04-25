package com.example.springcore.external;

import com.example.springcore.service.BusinessService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("B")
@Component("action")
public final class BAction extends AbstractExternalAction {

  BAction(BusinessService businessService) {
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
      return "[Action]:B, [Service]:Success";
    }

    return null;
  }

  @Override
  public void created() {
    System.out.println("B custom created");
  }

  @Override
  public void success() {
    System.out.println("B custom success");
  }

  @Override
  public void update() {
    System.out.println("B custom update");
  }

  @Override
  public boolean match() {
    System.out.println("B custom match");
    return true;
  }
}
