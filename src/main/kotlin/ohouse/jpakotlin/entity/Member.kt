package ohouse.jpakotlin.entity

import javax.persistence.*

@Entity
@Table(name = "members")
class Member private constructor(
    email: String,
    password: String,

    name: String,
    age: Int,
    phoneNumber: String,
    address: String?
) : BaseEntity() {

    // Id는 생성자로 받을 필요가 없으므로 빼줌
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null // Id는 save 시 값을 넣어주지 않으므로 nullable 해야 함

    // var 프로퍼티는 setter를 protected로 설정하기 위해 필드에서 선언
    @Column(unique = true)
    var email: String = email
        protected set
    var password: String = password
        protected set

    // 탈퇴나 휴면 전환 시 null로 세팅(name으로 조회 시 나오지 않도록)
    // 더이상 사용하지 않아도 DB에 남겨놔야 하기 때문에 null 허용해서 처리
    var name: String? = name
        protected set
    var age: Int = age
        protected set
    var phoneNumber: String = phoneNumber
        protected set
    var address: String? = address
        protected set

    companion object {
        // 정적 팩토리 메서드
        fun of(
            email: String,
            password: String,
            name: String,
            age: Int,
            phoneNumber: String,
            address: String? = null,
        ) =
            Member(email, password, name, age, phoneNumber, address)
    }

    fun updateInfo(
        email: String,
        password: String,
        name: String,
        age: Int,
        phoneNumber: String,
        address: String?,
    ) {
        this.email = email
        this.password = password
        this.name = name
        this.age = age
        this.phoneNumber = phoneNumber
        this.address = address
    }

    override fun remove() {
        super.remove()
        name = null
    }
}