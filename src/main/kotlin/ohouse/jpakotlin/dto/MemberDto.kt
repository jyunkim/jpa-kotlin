package ohouse.jpakotlin.dto

import ohouse.jpakotlin.entity.Member

object MemberDto {

    // post 요청 시 ObjectMapper를 사용하기 때문에 setter 필요x
    data class Request(
        val email: String,
        val password: String,

        val name: String,
        val age: Int,
        val phoneNumber: String,
        val address: String?
    ) {

        fun toEntity(): Member =
            Member.of(email, password, name, age, phoneNumber, address)
    }

    data class Response(
        val email: String,
        val password: String,

        val name: String?,
        val age: Int,
        val phoneNumber: String,
        val address: String?
    ) {

        companion object {
            fun of(member: Member) =
                Response(
                    member.email,
                    member.password,
                    member.name,
                    member.age,
                    member.phoneNumber,
                    member.address
                )
        }
    }

    data class Result(
        val success: Boolean,
        val message: String?
    )
}