package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "categories")
class Category private constructor(
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Category?
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @OneToMany(mappedBy = "parent")
    val children: MutableList<Category> = ArrayList()

    companion object {
        fun of(name: String, parent: Category? = null): Category {
            val category = Category(name, parent)
            parent?.children?.add(category)
            return category
        }
    }
}