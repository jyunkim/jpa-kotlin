package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "options")
class Option private constructor(
    val optionName: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(optionName: String) =
            Option(optionName)
    }
}
