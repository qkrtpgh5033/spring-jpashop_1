package jpabook.jpashop.service;

import jpabook.jpashop.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        /**
         * Item item = itemRepository.findOne(itemId); -> 영속상태 조회(핵심)
         * set 메서드 호출보다는
         * item.change() 와 같은 메소드를 만들어서 호출하는게 더 나음, 나중에 변경이 이러나는 부분을 추적하기 쉬워서
          */
        Item item = itemRepository.findOne(itemId);

        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);

    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

}
