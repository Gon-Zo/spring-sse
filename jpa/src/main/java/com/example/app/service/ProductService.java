package com.example.app.service;

import com.example.app.constract.State;
import com.example.app.service.dto.InitDTO;

public interface ProductService {
    State initProductByUser(InitDTO dto, Long userId);
}
