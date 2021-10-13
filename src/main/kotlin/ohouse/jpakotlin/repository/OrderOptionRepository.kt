package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.OrderOption
import org.springframework.data.jpa.repository.JpaRepository

interface OrderOptionRepository : JpaRepository<OrderOption, Long> {
}