package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findAllByName(name: String): List<Member>

    fun findByEmail(email: String): Member?
}