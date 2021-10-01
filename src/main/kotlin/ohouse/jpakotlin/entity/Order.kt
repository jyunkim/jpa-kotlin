package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    val title: String
) : BaseEntity() {
    companion object {
        fun of(member: Member, title: String): Order {
            val now = LocalDateTime.now()
            return Order(member = member, title = title).apply {
                createdAt = now
                updatedAt = now
            }
        }
    }
}
