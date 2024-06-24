package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hello.hello_spring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() {
       memberRepository = new MemoryMemberRepository();
       memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void AfterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // Arrange
        // TODO: Initialize test data
        Member member = new Member();
        member.setName("hello");

        // Act
        // TODO: Call the method to be tested
        Long joinedId = memberService.join(member);

        // Assert
        // TODO: Verify the results
        Member foundMember = memberService.findOne(joinedId).orElseThrow();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    public void join_throwsException_whenDubplicateMember() {
        //Arrange
        Member member1 = new Member();
        member1.setName("duplicate");
        Member member2 = new Member();
        member2.setName("duplicate");

        //Act
        memberService.join(member1);

        //Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

    }

    @Test
    void findMembers() {
        // Arrange
        // TODO: Initialize test data

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
    }

    @Test
    void findOne() {
        // Arrange
        // TODO: Initialize test data

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
    }
}