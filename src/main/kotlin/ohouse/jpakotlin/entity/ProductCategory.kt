package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "product_categories")
class ProductCategory private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(product: Product, category: Category) =
            ProductCategory(product, category)
    }
}
