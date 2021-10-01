package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "payments")
data class Payment(
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
        fun of(orderOption: OrderOption, paymentMethod: PaymentMethod, price: Int): Payment {
            val now = LocalDateTime.now()
            return Payment(orderOption = orderOption, paymentMethod = paymentMethod, price = price).apply {
                createdAt = now
                updatedAt = now
            }
        }
    }
}
