package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     *
     * @param member
     * @return member ID
     */
    public Long join(Member member) {
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("timeMs = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {

        memberRepository.findByName(member.getName()) // 여기까지 Optional 객체. ifPretent()메서드 사용 가능.
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
                });

    }

    /**
     * 전체 회원 조회
     *
     * @return List of members
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    /**
     * 회원 ID로 회원 조회
     *
     * @param memberID
     * @return Optional of member
     */
    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }
}
