package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 포함에 따른 애노테이션으로 매핑
    private Address address;

    @OneToMany(mappedBy = "member") // mappedBy -> 매핑하는애가 아니라 매핑 당하는 입장
    private List<Order> orders = new ArrayList<>();




}
