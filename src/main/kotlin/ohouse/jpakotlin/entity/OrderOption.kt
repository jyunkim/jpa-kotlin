package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "order_options")
class OrderOption private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    val option: Option,

    val optionName: String,

    quantity: Int,
    unitPrice: Double,
    amount: Double
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "qty")
    var quantity: Int = quantity
        protected set
    var unitPrice: Double = unitPrice
        protected set
    var amount: Double = amount
        protected set

    companion object {
        fun of(order: Order,
            option: Option,
            optionName: String,
            quantity: Int,
            unitPrice: Double,
            amount: Double? = null
        ): OrderOption {
            val totalPrice = amount ?: (quantity * unitPrice)
            return OrderOption(order, option, optionName, quantity, unitPrice, totalPrice)
        }
    }
}
