package ohouse.jpakotlin.entity

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class MemberEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Test
    @Commit
    fun memberMethodsTest() {
        val member = Member.of("asdf@gmail.com", "kim", 22)
        em.persist(member)

        val member1 = em.find(Member::class.java, member.id)
        println(member1)

        member1.updateInfo("lee", 23)
        em.flush()
        em.clear()

        val member2 = em.find(Member::class.java, member.id)
        println(member2)
        assertThat(member2.name).isEqualTo("lee")
        assertThat(member2.updatedAt).isNotEqualTo(member2.createdAt)

        member2.remove()
        em.flush()
        em.clear()

        val member3 = em.find(Member::class.java, member.id)
        println(member3)
        assertThat(member3.isRemoved).isTrue
        assertThat(member3.name).isNull()
    }
}
