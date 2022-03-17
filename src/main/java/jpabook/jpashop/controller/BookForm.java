package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id; // 상품수정 기능 떄문에 넣음

    private String name;
    private int price;
    private int stockQuantity;

    /**
     * Book
     */
    private String author;
    private String isbn;

}
