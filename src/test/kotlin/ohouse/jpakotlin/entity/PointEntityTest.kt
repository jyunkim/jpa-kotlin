package ohouse.jpakotlin.entity

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    fun usePointTest() {
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

        point2.usePoint(300)
        em.flush()
        em.clear()

        val point3 = em.find(Point::class.java, point.id)
        assertThat(point3.point).isEqualTo(200)
        assertThat(point3.usedPoint).isEqualTo(800)
    }

    @Test
    fun useNotEnoughPointTest() {
        val member = Member.of("asdf@gmail.com", "kim", 22)
        em.persist(member)

        val point = Point.of(member, null, 1000, LocalDateTime.now().plusMonths(1))
        em.persist(point)

        val point1 = em.find(Point::class.java, point.id)
        assertThrows<IllegalStateException> { point1.usePoint(1100) }
        assertThat(point1.point).isEqualTo(1000)
        assertThat(point1.usedPoint).isEqualTo(0)
    }
}
