package com.example.springtransaction.service;

import com.example.springtransaction.constract.State;
import com.example.springtransaction.service.dto.InitDTO;

public interface ProductService {
    State initProductByUser(InitDTO dto, Long userId);
}
