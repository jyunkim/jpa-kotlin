package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "products")
class Product private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    val brand: Brand,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category,

    name: String,
    val price: Int,
    description: String?
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var name: String = name
        protected set

    @Lob
    var description: String? = description
        protected set

    companion object {
        fun of(brand: Brand, category: Category, name: String, price: Int, description: String? = null) =
            Product(brand, category, name, price, description)
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun updateInfo(description: String) {
        this.description = description
    }
}
