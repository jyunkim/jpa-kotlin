package ohouse.jpakotlin

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)
}