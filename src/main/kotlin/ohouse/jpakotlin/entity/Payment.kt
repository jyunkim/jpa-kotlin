package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "payments")
class Payment private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_option_id")
    val orderOption: OrderOption,

    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,

    val price: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(orderOption: OrderOption, paymentMethod: PaymentMethod, price: Int) =
            Payment(orderOption, paymentMethod, price)
    }
}
