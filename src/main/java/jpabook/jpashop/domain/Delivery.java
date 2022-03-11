package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    // EnumType.Oridinal -> 상수로 들어감
    // 상수로 쓰면 Ready, xxx, COMP의 경우 값이 달라지기 때문에
    // String으로 쓰는걸 권장
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //Reday, COMP
}
