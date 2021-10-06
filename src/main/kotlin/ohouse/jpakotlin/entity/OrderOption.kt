package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "order_options")
class OrderOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    val option: Option,

    val optionName: String,

    @Column(name = "qty")
    val quantity: Int,
    val unitPrice: Double,
    val amount: Double
) : BaseEntity() {
    companion object {
        fun of(
            order: Order,
            option: Option,
            optionName: String,
            quantity: Int,
            unitPrice: Double,
            amount: Double
        ): OrderOption {
            val now = LocalDateTime.now()
            return OrderOption(
                order = order,
                option = option,
                optionName = optionName,
                quantity = quantity,
                unitPrice = unitPrice,
                amount = amount
            ).apply {
                createdAt = now
                updatedAt = now
            }
        }
    }
}
