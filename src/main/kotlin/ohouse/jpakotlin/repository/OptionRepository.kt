package ohouse.jpakotlin.repository

import ohouse.jpakotlin.entity.Option
import org.springframework.data.jpa.repository.JpaRepository

interface OptionRepository : JpaRepository<Option, Long> {
}