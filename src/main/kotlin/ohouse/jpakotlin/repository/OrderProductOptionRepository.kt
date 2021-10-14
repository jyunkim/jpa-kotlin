package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.OrderProductOption
import org.springframework.data.jpa.repository.JpaRepository

interface OrderProductOptionRepository : JpaRepository<OrderProductOption, Long> {
}