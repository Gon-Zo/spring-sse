package com.example.app.service;

import com.example.app.constract.State;
import com.example.app.domain.Item;
import com.example.app.domain.ItemUser;
import com.example.app.domain.Thing;
import com.example.app.repository.ItemRepository;
import com.example.app.repository.ItemUserRepository;
import com.example.app.repository.ThingRepository;
import com.example.app.service.dto.InitDTO;
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
