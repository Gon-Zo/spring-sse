package com.example.app.service;

import com.example.app.constract.State;
import com.example.app.service.dto.InitDTO;

public interface ItemService {

  State initUser(InitDTO dto, Long userId);

  Integer updateState(InitDTO dto, Long userId);
}
