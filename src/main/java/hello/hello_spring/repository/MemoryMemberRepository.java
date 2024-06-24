package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // Member 객체의 id를 key로 사용할 것임.
    // key는 Long 타입, value는 Member 객체. 여기까지만 작성해서는 아직 key의 타입만 정해졌을뿐, 무엇을 key값으로 사용할지는 미정임.
    // 데이터 저장소. 단순하게 메모리에.
    private static long sequence = 0L;
    // id 생성용. map에서 키값으로도 사용됨.

    @Override   // name은 고객이 가입할 때 이미 입력했으므로, 시스템 등록에 필요한 sequence만 적용시키면 된다.
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    } // member 객체의 id 값을 초기화하고, 이 id 값을 키로 해당 member 객체를 Map에 저장한 뒤, 해당 객체를 반환하는 메서드.

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    /*
    id는 Map의 키값이라서, store.get(id) 메서드를 통해 id값(key값)에 해당하는 객체를 찾을 수 있음.
    키값(id)와 일치하는 객체가 없으면 null을 반환해야 하므로 Optional으로 감싸서 반환.
    */

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    } /*
    id와 달리, name은 Map의 key 값이 아니다. 따라서 Map 객체(store)에 저장된 모든 member 객체를 순회하면서, 거기 들어있는
    name 값과 하나씩 대조해봐야 한다.
    */

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    } // List 객체가 다루기 쉬우니까 Map을 List로 변환하여 반환한다.

    public void clearStore() {
        store.clear();
    }
}
