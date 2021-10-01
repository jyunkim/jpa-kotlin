package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "points")
data class Point(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    val reason: String?,

    val point: Int,
    val usedPoint: Int = 0,

    val expiredAt: LocalDateTime
) : BaseEntity() {
    companion object {
        fun of(
            member: Member,
            reason: String?,
            point: Int,
            usedPoint: Int,
            expiredAt: LocalDateTime
        ): Point {
            val now = LocalDateTime.now()
            return Point(
                member = member,
                reason = reason,
                point = point,
                usedPoint = usedPoint,
                expiredAt = expiredAt
            ).apply {
                createdAt = now
                updatedAt = now
            }
        }
    }
}
