package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /*
    findByName 메서드: MemberRepository 인터페이스에서 이미 선언되어 있지만, SpringDataJpaMemberRepository 인터페이스에서
    다시 한 번 명시적으로 선언함으로써 Spring Data JPA가 이를 쿼리 메서드로 인식하도록 함.
    Spring Data JPA는 메서드 이름을 분석하여 자동으로 쿼리를 생성
    findByName에서 Name
    */
    @Override
    Optional<Member> findByName(String name);
}
