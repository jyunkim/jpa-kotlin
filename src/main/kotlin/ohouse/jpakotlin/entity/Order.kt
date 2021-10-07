package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "orders")
class Order private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    val title: String
) : BaseEntity() {

    companion object {
        fun of(member: Member, title: String) =
            Order(member = member, title = title)
    }
}
