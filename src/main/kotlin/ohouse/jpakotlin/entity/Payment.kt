package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "payments")
class Payment private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_option_id")
    val orderOption: OrderOption,

    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,

    val price: Int
) : BaseEntity() {

    companion object {
        fun of(orderOption: OrderOption, paymentMethod: PaymentMethod, price: Int) =
            Payment(orderOption = orderOption, paymentMethod = paymentMethod, price = price)
    }
}
