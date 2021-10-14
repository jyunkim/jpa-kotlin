package ohouse.jpakotlin.entity

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class CategoryEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Test
    fun categoryTest() {
        val parent = Category.of("가전")
        em.persist(parent)
        val child = Category.of("핸드폰", parent)
        em.persist(child)

        em.flush()
        em.clear()

        val findParent = em.find(Category::class.java, parent.id)
        val findChild = em.find(Category::class.java, child.id)

        assertThat(findChild.parent!!.name).isEqualTo("가전")
        assertThat(findParent.children.size).isEqualTo(1)
        assertThat(findParent.children[0].name).isEqualTo("핸드폰")
    }
}