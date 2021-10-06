package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class BaseEntity {

    @Column(updatable = false)
    var createdAt: LocalDateTime? = null
        protected set // open 프로퍼티는 private setter를 가질 수 없음
    var updatedAt: LocalDateTime? = null
        protected set
    var isRemoved: Boolean = false
        protected set

    @PrePersist
    fun prePersist() {
        // 따로 초기화하면 createdAt과 updatedAt에 차이가 생김
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }

    fun remove() {
        isRemoved = true
    }
}
