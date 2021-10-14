package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "brands")
class Brand private constructor(
    val name: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(name: String) =
            Brand(name)
    }
}