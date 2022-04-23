package com.example.springcore.bean;

import com.example.springcore.service.BusinessService;

public abstract class AbstractAction implements Action {
  private final BusinessService businessService;

  protected AbstractAction(BusinessService businessService) {
    this.businessService = businessService;
  }

  public BusinessService getBusinessService(){
    return businessService;
  }
}
