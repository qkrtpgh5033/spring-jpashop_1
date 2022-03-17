package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     *
     * @GetMapping -> 폼 화면 열어보기
     * @PostMapping -> 데이터 처리
     * @Valid -> validation과 관련된 기능수행 ex) @NotEmpty
     * BindingResult -> 페이지에서 오류가 나면 원래 튕김
     * BindingResult를 씀으로써 오류내용이 담기기 때문에 이를 통해서 코드 작성이 가능
     */

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @GetMapping("/members")
    public String createList(Model model){
        List<Member> list = memberService.findMembers();
        model.addAttribute("members", list);
        return "members/member_List";
    }

    /**
     * MemberForm이랑 Member 객체랑 필드가 비슷한데 굳이 만드는 이유( Member 엔티티를 파라미터로 안주고 MemberForm을 파라미터로 사용하는 이유)
     *
     * 두 개가 정확히 일치하지 않음(변수)
     * 근데도 불구하고 하나로 합칠 경우 @NotEmpty 등등 코드가 너무 지저분 해짐
     * 즉, 컨트롤러에서 넘어오는 Validation이랑 실제 Domain에서 원하는 Validation이랑
     * 다르기 때문에, 이를 분리하는게 보기 편함
     *
     * 그러니깐 데이터가 자주 이동하는 Form은 따로 만들어주는게 나음
     */
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);

        // 보통 저장되고 나면 재로딩은 안좋음 그래서 리다이렉트로 홈으로(첫번째 페이지) 보내버림
        return "redirect:/";
    }

}
