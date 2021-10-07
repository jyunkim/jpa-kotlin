package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "orders")
class Order private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    val title: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(member: Member, title: String) =
            Order(member, title)
    }
}
