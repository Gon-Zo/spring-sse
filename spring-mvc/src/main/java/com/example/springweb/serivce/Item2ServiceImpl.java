package com.example.springweb.serivce;

import com.example.springweb.domain.db1.Item1;
import com.example.springweb.domain.db2.Item2;
import com.example.springweb.repository.db1.Item1Repository;
import com.example.springweb.repository.db2.Item2Repository;
import com.example.springweb.serivce.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.springweb.config.db.DefaultValue.CHAINED_TRANSACTION_MANAGER;

@Slf4j
@Service(value = "item2Service")
@RequiredArgsConstructor
public class Item2ServiceImpl implements ItemService{

    private final Item1Repository item1Repository;

    private final Item2Repository item2Repository;

    // chained transaction
    // first transaction this db1 work
    // second transaction this db2 work
    @Override
    @Transactional(value = CHAINED_TRANSACTION_MANAGER, rollbackFor = Exception.class)
    public int update(long id, ItemDTO dto) {

        log.info("item2Service work");

        Item1 item1 = updateItem1(id, dto);

        Item2 item2 = updateItem2(item1);

        return 1;
    }

    Item1 updateItem1(long id , ItemDTO dto){
        Item1 item1 = item1Repository.findById(id).orElseThrow();

        item1.updateName(dto.getName());

        return item1;
    }

    Item2 updateItem2(Item1 item1){
        Long item2Id = item1.getItem2Id();

        Item2 item2 = item2Repository.findById(item2Id).orElseThrow();

        item2.updateName(item1.getName());

        return item2;
    }
}
