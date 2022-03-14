package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // readOnly -> 조회 최적화
// jpa 모든 ★데이터 변경★이나 로직들은 "Transactional" 안에서 수행
public class MemberService {

//    @Autowired // 스프링이 스프링 빈에 등록된 "MemberRepository" 의존관계 주입
    private final MemberRepository memberRepository;

    @Autowired // 생성자 의존관계 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */

    @Transactional // -> 한번 더 쓴 이유 readOnly true를 막기 위해, 쓰기는 쓰면 안됨 (디폴트 값이 false)
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION 예외처리
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) // 이미 회원 존재
        {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    /**
     * 회원 조회
     */

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 한건 조회
    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
