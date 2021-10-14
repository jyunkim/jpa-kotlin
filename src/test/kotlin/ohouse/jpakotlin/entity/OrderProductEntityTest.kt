package ohouse.jpakotlin.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class OrderProductEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Test
    @Commit
    fun orderTest() {
        val member = em.find(Member::class.java, 1L)
        val productOption = em.find(ProductOption::class.java, 1L)

        // 주문
        val order = Order.of(
            member,
            OrderStatus.READY,
            PaymentMethod.CARD,
            "서울시 강남구"
        )
        val orderProduct = OrderProduct.of(order, productOption, 1, 0)
        productOption.removeStock(1)
        em.persist(order)
        em.persist(orderProduct)

        em.flush()
        em.clear()

        val findProductOption = em.find(ProductOption::class.java, productOption.id)

        Assertions.assertThat(findProductOption.stockQuantity).isEqualTo(9)
    }

    @BeforeEach
    fun before() {
        val member = Member.of(
            "asdf@gmail.com",
            "1234",
            "kim",
            25,
            "01012341234"
        )
        em.persist(member)

        val brand = Brand.of("삼성")
        em.persist(brand)

        val category = Category.of("가전")
        em.persist(category)

        val product = Product.of(
            member,
            brand,
            category,
            "갤럭시s20",
            100000
        )
        em.persist(product)

        val productOption = ProductOption.of(product, "플러스", 120000, 10)
        em.persist(productOption)
    }
}