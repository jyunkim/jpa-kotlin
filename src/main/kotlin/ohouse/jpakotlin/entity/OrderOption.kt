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

    val unitPrice: Double,
    quantity: Int,
    amount: Double
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "qty")
    var quantity: Int = quantity
        protected set
    var amount: Double = amount
        protected set

    companion object {
        fun of(order: Order,
            option: Option,
            optionName: String,
            unitPrice: Double,
            quantity: Int,
            amount: Double? = null
        ): OrderOption {
            val totalPrice = amount ?: (unitPrice * quantity)
            return OrderOption(order, option, optionName, unitPrice, quantity, totalPrice)
        }
    }

    fun changeQuantity(quantity: Int) {
        this.quantity = quantity
        amount = unitPrice * quantity
    }
}
