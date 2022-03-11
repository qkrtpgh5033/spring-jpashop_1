package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item { // 상속관계 이기 때문에 abstarct 사용

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    // 기본 속성들
    private String name;
    private int price;
    private int stockQuantity;

    //실무에선 잘 안씀 다대다 관계 테이블 속성값에 한계때문
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
