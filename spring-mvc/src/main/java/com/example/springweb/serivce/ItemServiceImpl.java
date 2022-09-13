package com.example.springweb.serivce;


import com.example.springweb.domain.db1.Item1;
import com.example.springweb.domain.db2.Item2;
import com.example.springweb.repository.db1.Item1Repository;
import com.example.springweb.repository.db2.Item2Repository;
import com.example.springweb.serivce.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.example.springweb.config.db.DefaultValue.*;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final Item1Repository item1Repository;

    private final Item2Repository item2Repository;

    @Override
    @Transactional(value = DB1_TRANSACTION_MANAGER, rollbackFor = Exception.class)
    public int update(long id, ItemDTO dto) {

        log.info("itemService work");

        Item1 item1 = updateItem1(id, dto);

        Item2 item2 = updateItem2(item1);

        return 1;
    }

    @Transactional(value = DB1_TRANSACTION_MANAGER , rollbackFor = Exception.class)
    Item1 updateItem1(long id , ItemDTO dto){
        Item1 item1 = item1Repository.findById(id).orElseThrow();

        item1.updateName(dto.getName());

        return item1;
    }

    @Transactional(value = DB2_TRANSACTION_MANAGER , rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    Item2 updateItem2(Item1 item1){
        Long item2Id = item1.getItem2Id();

        Item2 item2 = item2Repository.findById(item2Id).orElseThrow();

        item2.updateName(item1.getName());

        return item2;
    }
}
