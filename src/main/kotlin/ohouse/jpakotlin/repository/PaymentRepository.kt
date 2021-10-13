package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long> {
}