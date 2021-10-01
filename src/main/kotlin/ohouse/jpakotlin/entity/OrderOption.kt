package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "order_options")
data class OrderOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    val option: Option,

    val optionName: String,
    val unitPrice: Double,

    @Column(name = "qty")
    var quantity: Int,
    var amount: Double
) : BaseEntity(LocalDateTime.now())
