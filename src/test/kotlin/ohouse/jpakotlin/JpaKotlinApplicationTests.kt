package ohouse.jpakotlin

import ohouse.jpakotlin.entity.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class JpaKotlinApplicationTests {

    @Autowired
    lateinit var em: EntityManager

    @Test
    @Commit
    fun contextLoads() {
        val member = Member(0, "asdf@naver.com", "kim")
        em.persist(member)

        val order = Order(0, member, "asdf")
        em.persist(order)

        val option = Option(0, "asdf")
        em.persist(option)

        val orderOption = OrderOption(0, order, option, "asdf", 100.0, 10, 1000.0)
        em.persist(orderOption)

        val payment = Payment(0, orderOption, PaymentMethod.CARD, 100)
        em.persist(payment)

        em.flush()
        em.clear()

        val order1 = em.find(Order::class.java, order.id)
        println(order1.title)
    }

}
