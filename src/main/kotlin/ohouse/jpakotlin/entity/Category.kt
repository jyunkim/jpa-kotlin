package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "categories")
class Category private constructor(
    val name: String,

    parent: Category?
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = parent
        protected set

    @OneToMany(mappedBy = "parent")
    val children: MutableList<Category> = mutableListOf()

    companion object {
        fun of(name: String, parent: Category? = null) =
            Category(name, parent)
    }

    fun addChildCategory(category: Category) {
        children.add(category)
        category.parent = this
    }
}
