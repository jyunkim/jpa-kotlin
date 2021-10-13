package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}