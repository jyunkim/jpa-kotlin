package ohouse.jpakotlin.entity

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class MemberEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Test
    fun memberMethodsTest() {
        val member = Member.of(
            "asdf@gmail.com",
            "1234",
            "kim",
            25,
            "01012341234"
            )
        em.persist(member)

        val member1 = em.find(Member::class.java, member.id)
        val newMember = Member.of(
            member1.email,
            member1.password,
            "lee",
            24,
            member1.phoneNumber,
            member1.address
        )
        member1.updateInfo(newMember)
        em.flush()
        em.clear()

        val member2 = em.find(Member::class.java, member.id)
        assertThat(member2.name).isEqualTo("lee")
        assertThat(member2.updatedAt).isNotEqualTo(member2.createdAt)

        member2.remove()
        em.flush()
        em.clear()

        val member3 = em.find(Member::class.java, member.id)
        assertThat(member3.isRemoved).isTrue
        assertThat(member3.name).isNull()
    }
}
