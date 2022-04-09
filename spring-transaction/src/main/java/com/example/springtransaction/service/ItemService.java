package com.example.springtransaction.service;

import com.example.springtransaction.constract.State;
import com.example.springtransaction.service.dto.InitDTO;

public interface ItemService {

  State initUser(InitDTO dto, Long userId);

  Integer updateState(InitDTO dto, Long userId);
}
