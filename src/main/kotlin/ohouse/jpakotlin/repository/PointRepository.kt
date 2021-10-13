package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Point
import org.springframework.data.jpa.repository.JpaRepository

interface PointRepository : JpaRepository<Point, Long> {
}