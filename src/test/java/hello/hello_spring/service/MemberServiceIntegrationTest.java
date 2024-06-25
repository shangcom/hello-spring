package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void join() {
        // Arrange
        // TODO: Initialize test data
        Member member = new Member();
        member.setName("spring3");

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