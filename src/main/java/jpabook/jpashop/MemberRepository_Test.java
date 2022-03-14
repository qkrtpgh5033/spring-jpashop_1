package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Repository -> Entity를 찾아줌 (DAO랑 비슷한 역할)
// DAO : Data Access Object
@Repository
public class MemberRepository_Test {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId(); // 김영한 강사님 스타일 -> 쿼리와 커맨드를 분리
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
