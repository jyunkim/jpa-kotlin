package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(
    val createdAt: LocalDateTime
)
