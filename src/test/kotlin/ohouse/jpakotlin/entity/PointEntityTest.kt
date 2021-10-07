package ohouse.jpakotlin.entity

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class PointEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Test
    @Commit
    fun pointMethodTest() {
        val member = Member.of("asdf@gmail.com", "kim", 22)
        em.persist(member)

        val point = Point.of(member, null, 1000, LocalDateTime.now().plusMonths(1))
        em.persist(point)

        val point1 = em.find(Point::class.java, point.id)
        point1.usePoint(500)
        em.flush()
        em.clear()

        val point2 = em.find(Point::class.java, point.id)
        assertThat(point2.point).isEqualTo(500)
    }
}
