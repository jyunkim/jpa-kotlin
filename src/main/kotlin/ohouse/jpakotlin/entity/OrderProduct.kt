package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "order_products")
class OrderProduct private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id")
    val productOption: ProductOption,

    val quantity: Int,

    val deliveryFee: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(order: Order, productOption: ProductOption, quantity: Int, deliveryFee: Int) =
            OrderProduct(order, productOption, quantity, deliveryFee)
    }
}