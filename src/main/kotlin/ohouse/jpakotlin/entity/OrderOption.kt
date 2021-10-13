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
    totalPrice: Double
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "qty")
    var quantity: Int = quantity
        protected set
    var totalPrice: Double = totalPrice
        protected set

    companion object {
        fun of(
            order: Order,
            option: Option,
            optionName: String,
            unitPrice: Double,
            quantity: Int,
            totalPrice: Double? = null
        ): OrderOption {
            val amount = totalPrice ?: (unitPrice * quantity)
            option.removeStock(quantity)

            return OrderOption(order, option, optionName, unitPrice, quantity, amount)
        }
    }

    fun changeQuantity(quantity: Int) {
        this.quantity = quantity
        totalPrice = unitPrice * quantity
    }
}
