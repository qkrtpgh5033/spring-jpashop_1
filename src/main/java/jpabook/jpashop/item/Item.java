package jpabook.jpashop.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
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


    //==비즈니스 로직==//

    /**
     *  setter를 통해서 계산한다음 stock 변수에 값을 넣는 것이 아니라
     *  안에서 비즈니스 로직을 사용하여 응집도를 높이는게 더 좋다.
     *  즉, 도메인 스스로 해결할 수 있는 경우는 도메인 안에서 해결
     */
    /**
     * stock 증가
     */

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stcok 감소
     */
    public void removeStock(int quantity){

        int resStock = this.stockQuantity - quantity;
        if(resStock < 0 ){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resStock;
    }



}
