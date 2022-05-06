package com.example.springcore.service.external;

import com.example.springcore.service.BusinessService;

public abstract class AbstractExternalAction
    implements ExternalAction, ExternalCreated, ExternalMatcher, ExternalSuccess {
  private final BusinessService businessService;

  protected AbstractExternalAction(BusinessService businessService) {
    this.businessService = businessService;
  }

  protected BusinessService getBusinessService() {
    return businessService;
  }

  @Override
  public void success() {
    System.out.println("default success");
  }

  @Override
  public void update() {
    System.out.println("default update");
  }

  @Override
  public void created() {
    System.out.println("default created");
  }

  @Override
  public boolean match() {
    System.out.println("default matcher");
    return false;
  }
}
