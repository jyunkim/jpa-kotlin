package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "options")
class Option private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    val name: String,
    val price: Int,
    stockQuantity: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var stockQuantity: Int = stockQuantity
        protected set

    companion object {
        fun of(product: Product, name: String, price: Int, stockQuantity: Int) =
            Option(product, name, price, stockQuantity)
    }

    fun addStock(quantity: Int) {
        stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        if (stockQuantity < quantity) throw IllegalArgumentException("재고가 부족합니다.")
        stockQuantity -= quantity
    }
}
