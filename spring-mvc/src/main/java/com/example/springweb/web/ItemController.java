package com.example.springweb.web;

import com.example.springweb.serivce.ItemService;
import com.example.springweb.serivce.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    private final ItemService item2Service;

    public ItemController(ItemService itemService, @Qualifier("item2Service") ItemService item2Service) {
        this.itemService = itemService;
        this.item2Service = item2Service;
    }


    @PatchMapping("{id}")
    public int updateItem(@PathVariable Long id , @RequestBody ItemDTO dto){
        return itemService.update(id , dto);
    }

    @PatchMapping("/test/{id}")
    public int updateItem1(@PathVariable Long id , @RequestBody ItemDTO dto){
        return item2Service.update(id , dto);
    }
}
