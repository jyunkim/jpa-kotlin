package ohouse.jpakotlin.entity

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class OrderOptionEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Test
    @Commit
    fun orderOptionMethodTest() {
        val member = Member.of("asdf@gmail.com", "kim", 22)
        em.persist(member)

        val order = Order.of(member, "orderA")
        em.persist(order)

        val option = Option.of("optionA")
        em.persist(option)

        val orderOption = OrderOption.of(order, option, "optionA", 1000.0, 2)
        em.persist(orderOption)

        val payment = Payment.of(orderOption, PaymentMethod.CARD, 200)
        em.persist(payment)

        val orderOption1 = em.find(OrderOption::class.java, orderOption.id)
        orderOption1.changeQuantity(3)
        em.flush()
        em.clear()

        val orderOption3 = em.find(OrderOption::class.java, orderOption.id)
        assertThat(orderOption3.quantity).isEqualTo(3)
        assertThat(orderOption3.amount).isEqualTo(orderOption3.unitPrice * 3)
    }
}
