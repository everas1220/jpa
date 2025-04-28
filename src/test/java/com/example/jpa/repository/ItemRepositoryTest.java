package com.example.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.jpa.entity.QItem;
import com.example.jpa.entity.Item.ItemStatus;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void queryDslTest() {

        QItem item = QItem.item;
        System.out.println(itemRepository.findAll(item.itemNm.eq("item2")));

        // where itemNm like 'item2%'
        System.out.println(itemRepository.findAll(item.itemNm.startsWith("item2")));
        // where itemNm like '%item2'
        System.out.println(itemRepository.findAll(item.itemNm.endsWith("item2")));
        // where itemNm like '%item2%'
        System.out.println(itemRepository.findAll(item.itemNm.contains("item2")));
        // where itemNm = 'item2' and price > 1000
        System.out.println(itemRepository.findAll(item.itemNm.eq("item").and(item.price.gt(1000))));
        // where itemNm = 'item2' and price >= 1000
        System.out.println(itemRepository.findAll(item.itemNm.eq("item").and(item.price.goe(1000))));
        // where itemNm like '%item2%' or itemSellStatus = SOLD_OUT
        System.out.println(
                itemRepository.findAll(item.itemNm.contains("item2").or(item.itemSellStatus.eq(ItemStatus.SOLD_OUT))));
        // where stockNumber >= 30
        System.out.println(itemRepository.findAll(item.stockNumber.goe(30)));
        // where price < 35000
        System.out.println(itemRepository.findAll(item.price.lt(35000)));

        // 조건 : BooleanBuilder
        BooleanBuilder builder = new BooleanBuilder();
        // where itemNm = 'item2' and price > 1000
        builder.and(item.itemNm.eq("item2"));
        builder.and(item.price.gt(1000));
        System.out.println(itemRepository.findAll(builder));
    }

}
