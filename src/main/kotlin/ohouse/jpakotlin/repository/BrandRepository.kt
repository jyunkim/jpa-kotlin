package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<Brand, Long> {
}