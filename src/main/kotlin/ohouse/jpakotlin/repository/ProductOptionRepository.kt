package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.ProductOption
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionRepository : JpaRepository<ProductOption, Long> {
}