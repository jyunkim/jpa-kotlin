package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
    var isDeleted: Boolean = false
}
