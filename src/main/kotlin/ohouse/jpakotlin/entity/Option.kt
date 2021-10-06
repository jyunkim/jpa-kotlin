package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "options")
class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val optionName: String
)
