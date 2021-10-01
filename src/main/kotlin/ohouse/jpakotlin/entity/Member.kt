package ohouse.jpakotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "members")
// 양방향 연관관계가 없기 때문에 data class 사용
// 상속받은 필드는 자동으로 생성되는 메서드에 포함되지 않음
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // Id는 save 시 값을 넣어주지 않으므로 nullable 해야 함

    // var을 사용하지 않고 정보 수정 시에는 data class의 copy() 이용
    // spring data jpa의 save()를 사용할 때 인자로 id값이 있는 엔티티가 전달되면 insert 쿼리가 나가지 않고 update 쿼리가 나감
    @Column(unique = true)
    val email: String,

    // 탈퇴나 휴면 전환 시 null로 세팅(name으로 조회 시 나오지 않도록)
    // 더이상 사용하지 않아도 DB에 남겨놔야 하기 때문에 null 허용해서 처리
    val name: String?
) : BaseEntity() {
    companion object {
        // 정적 팩토리 메서드
        fun of(email: String, name: String): Member {
            // 따로 초기화하면 createdAt과 updatedAt에 차이가 생김
            val now = LocalDateTime.now()
            return Member(email = email, name = name).apply {
                createdAt = now
                updatedAt = now
            }
        }
    }
}
