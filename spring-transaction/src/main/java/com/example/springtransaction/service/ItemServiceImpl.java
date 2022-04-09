package com.example.springtransaction.service;

import com.example.springtransaction.constract.State;
import com.example.springtransaction.domain.Item;
import com.example.springtransaction.domain.ItemUser;
import com.example.springtransaction.domain.Thing;
import com.example.springtransaction.repository.ItemRepository;
import com.example.springtransaction.repository.ItemUserRepository;
import com.example.springtransaction.repository.ThingRepository;
import com.example.springtransaction.service.dto.InitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  private final ItemUserRepository itemUserRepository;

  private final ThingRepository thingRepository;

  @Override
  @Retryable(
      maxAttempts = 3,
      backoff = @Backoff(delay = 2000),
      value = {StaleObjectStateException.class, RuntimeException.class})
  @Transactional(rollbackFor = Exception.class)
  public State initUser(InitDTO dto, Long userId) {

    Item item = itemRepository.findById(dto.getProductId()).orElseThrow();

    if (State.PROGRESS.equals(item.getState())) {

      Integer total = item.getTotalAmount();

      item.upCurrentAmount(dto.getAmount());

      Integer current = item.getCurrentAmount();

      if (current >= total) {
        item.soldOutState();
      }

      itemRepository.flush();

      ItemUser itemUser = dto.toEntity(userId, item);

      itemUserRepository.save(itemUser);
    }

    return item.getState();
  }

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public Integer updateState(InitDTO dto, Long userId) {

    Thing thing = thingRepository.findById(dto.getProductId()).orElseThrow();

    log.info("[UserId]={},[CurrentAmount]={}", userId, thing.getCurrentAmount());

    thing.upCurrentAmount(dto.getAmount());

    return thing.getCurrentAmount();
  }
}
