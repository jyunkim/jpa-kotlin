package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "points")
class Point private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    val reason: String?,

    point: Int,
    usedPoint: Int,

    val expiredAt: LocalDateTime
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var point: Int = point
        protected set
    var usedPoint: Int = usedPoint
        protected set

    companion object {
        fun of(
            member: Member,
            reason: String?,
            point: Int,
            expiredAt: LocalDateTime,
            usedPoint: Int = 0
        ): Point {
            return Point(member, reason, point, usedPoint, expiredAt)
        }
    }
}
