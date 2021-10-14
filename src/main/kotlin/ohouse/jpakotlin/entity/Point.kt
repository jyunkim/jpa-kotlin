package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "points")
class Point private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    val name: String,
    val reason: String?,

    val point: Int,
    val usedPoint: Int,

    val expiredAt: LocalDateTime
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(
            member: Member,
            name: String,
            reason: String?,
            expiredAt: LocalDateTime,
            point: Int = 0,
            usedPoint: Int = 0
        ): Point =
            Point(member, name, reason, point, usedPoint, expiredAt)
    }
}