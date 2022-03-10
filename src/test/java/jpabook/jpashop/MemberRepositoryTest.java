package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;


    @Test
    @Transactional // 엔티티 매니저를 통한 모든 데이터 변경은 @Transactional 안에서 동작해야 됨
                    // Test에서 Tansactional을 쓰면 테스트가 끝날 때 데이터를 롤백함 -> DB 저장이 안댐
    @Rollback(false)
    public void testMember() throws Exception{

        //given
        Member member = new Member();
        member.setUsername("MemberA");
       
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        System.out.println("findMember == member : " + (findMember == member));
        assertThat(findMember).isEqualTo(member);

    }
}