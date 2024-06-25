package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    /*
    EntityManager: EntityManagerFactory에서 생성되며, EntityManagerFactory는 DataSource를 포함한 여러 설정을 관리.
    */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> resultList = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
        return resultList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
        return resultList;
    }
}
