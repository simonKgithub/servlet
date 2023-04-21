package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려해야 함
 * */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용(공용)
    private static long sequence = 0L; //static 사용(공용)

    //static inner 클래스로 싱글톤 패턴 적용
    private static class MemberRepositoryHolder {
        private static final MemberRepository INSTANCE = new MemberRepository();
    }

    public static MemberRepository getInstance() {
        return MemberRepositoryHolder.INSTANCE;
    }

    private MemberRepository() {}

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
