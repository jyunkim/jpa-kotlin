package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "reviews")
class Review private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id")
    val productOption: ProductOption,

    comment: String,
    rating: Double
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Lob
    var comment: String = comment
        protected set
    var rating: Double = rating
        protected set

    companion object {
        fun of(
            member: Member,
            product: Product,
            productOption: ProductOption,
            comment: String,
            rating: Double
        ) =
            Review(member, product, productOption, comment, rating)
    }

    fun updateInfo(comment: String, rating: Double) {
        this.comment = comment
        this.rating = rating
    }
}