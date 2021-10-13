package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}