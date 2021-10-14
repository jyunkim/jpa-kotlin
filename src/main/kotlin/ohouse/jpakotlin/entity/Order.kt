package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "orders")
class Order private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    orderStatus: OrderStatus,

    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,

    val deliveryAddress: String,
    val deliveryRequest: String?,

    val usedPoint: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus = orderStatus
        protected set

    val totalCost: Int
        get() {
            TODO()
        }

    companion object {
        fun of(
            member: Member,
            orderStatus: OrderStatus,
            paymentMethod: PaymentMethod,
            deliveryAddress: String,
            deliveryRequest: String? = null,
            usedPoint: Int = 0
        ) =
            Order(member, orderStatus, paymentMethod, deliveryAddress, deliveryRequest, usedPoint)
    }

    fun updateStatus(orderStatus: OrderStatus) {
        this.orderStatus = orderStatus
    }

    fun cancel() {
        orderStatus = OrderStatus.CANCELED
    }
}