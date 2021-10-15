package ohouse.jpakotlin.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import ohouse.jpakotlin.dto.MemberDto
import ohouse.jpakotlin.repository.MemberRepository
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [MemberService::class]) // 해당 클래스만 주입
class MemberServiceTest(
    private val memberService: MemberService
) : BehaviorSpec() {

    @MockkBean
    private lateinit var memberRepository: MemberRepository

    init {
        Given("MemberDto가 있을 때") {
            val memberRequest = MemberDto.Request(
                "asdf@gmail.com",
                "1234",
                "kim",
                25,
                "01012341234",
                "서울시 강남구"
            )
            every { memberRepository.save(any()) } returns memberRequest.toEntity()
            When("회원가입 시 중복 회원이 없는 경우") {
                every { memberRepository.findByEmail(any()) } returns null
                val result = memberService.signUp(memberRequest)
                Then("정상 처리") {
                    result.success shouldBe true
                }
            }
        }
    }
}