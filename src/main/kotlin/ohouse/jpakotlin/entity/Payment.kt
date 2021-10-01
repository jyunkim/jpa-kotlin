package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "payments")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_option_id")
    val orderOption: OrderOption,

    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,

    val price: Int
) : BaseEntity(LocalDateTime.now())
