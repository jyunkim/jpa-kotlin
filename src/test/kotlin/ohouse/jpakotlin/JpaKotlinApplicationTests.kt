package ohouse.jpakotlin

import ohouse.jpakotlin.entity.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class JpaKotlinApplicationTests {

    @Autowired
    lateinit var em: EntityManager

    @Test
    @Commit
    fun contextLoads() {
        val member = Member.of("asdf@gmail.com", "kim")
        em.persist(member)

        val order = Order.of(member, "orderA")
        em.persist(order)

        val option = Option(optionName = "optionA")
        em.persist(option)

        val orderOption = OrderOption.of(order, option, "optionA", 2, 100.0, 200.0)
        em.persist(orderOption)

        val payment = Payment.of(orderOption, PaymentMethod.CARD, 200)
        em.persist(payment)

        em.flush()
        em.clear()

        val member1 = em.find(Member::class.java, member.id)
        println(member1)
    }

}