package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
}