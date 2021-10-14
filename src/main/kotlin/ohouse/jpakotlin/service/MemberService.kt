package ohouse.jpakotlin.service

import ohouse.jpakotlin.dto.MemberDto
import ohouse.jpakotlin.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository // private을 붙이면 open -> final됨
) {

    fun getProfile(id: Long): MemberDto.Response {
        val findMember = findMember(id)
        return MemberDto.Response.of(findMember)
    }

    @Transactional
    fun signUp(member: MemberDto.Request): MemberDto.Result {
        validate(member)
        memberRepository.save(member.toEntity())
        return MemberDto.Result(true, "회원가입이 완료되었습니다.")
    }

    @Transactional
    fun updateInfo(id: Long, member: MemberDto.Request): MemberDto.Result {
        val findMember = findMember(id)
        findMember.updateInfo(member.toEntity())
        return MemberDto.Result(true, "정보 수정이 완료되었습니다.")
    }

    @Transactional
    fun deleteAccount(id: Long): MemberDto.Result {
        val findMember = findMember(id)
        findMember.remove()
        return MemberDto.Result(true, "회원 탈퇴가 완료되었습니다.")
    }

    private fun findMember(id: Long) =
        memberRepository.findByIdOrNull(id) ?: throw IllegalStateException("회원이 존재하지 않습니다.")

    // 중복 회원 검증
    private fun validate(member: MemberDto.Request) {
        val findMember = memberRepository.findByEmail(member.email)
        findMember?.let { throw IllegalStateException("이미 존재하는 회원입니다.") }
    }
}