package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;

// db까지 엮어서 테스트하기 위해 스프링이랑 통합(RunWith, SpringBootTest
@RunWith(SpringRunner.class) // 스프링을 엮어서 실행
@SpringBootTest // 스프링 부트를 띄운 상태로 테스트 -> Autowired와 같은 애노테이션을 사용하기 위함.
@Transactional // 데이터 변경을 위함 (테스트에서는 변경 사항을 롤백시킴)
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName("userA");
        //when

        Long userID = memberService.join(member);
        //then
        em.flush(); // 쿼리문은 볼 수 있으나 롤백은 O
        Assertions.assertEquals(member, memberRepository.findOne(userID));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_화원_예외() throws Exception{

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생했습니다.(원래 여기에 도달하면 안됨)");

    }


}