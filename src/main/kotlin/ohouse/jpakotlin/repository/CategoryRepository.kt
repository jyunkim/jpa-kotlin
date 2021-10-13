package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}