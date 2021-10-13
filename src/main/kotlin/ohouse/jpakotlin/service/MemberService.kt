package ohouse.jpakotlin.service

import ohouse.jpakotlin.entity.Member
import ohouse.jpakotlin.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository // private을 붙이면 open -> final됨
) {

    @Transactional
    fun signup(member: Member): Long {
        validate(member)
        memberRepository.save(member)
        return member.id!!
    }

    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

//    fun findMember(id: Long): Member {
//        return memberRepository.findByIdOrNull(id)
//    }

    // 중복 회원 검증
    private fun validate(member: Member) {
        memberRepository.findByEmail(member.email).let { throw IllegalStateException("이미 존재하는 회원입니다.") }
    }
}