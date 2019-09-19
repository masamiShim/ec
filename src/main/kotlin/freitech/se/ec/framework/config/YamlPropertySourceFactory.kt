package freitech.se.ec.framework.config

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.env.PropertySource
import org.springframework.core.io.support.EncodedResource
import org.springframework.core.io.support.PropertySourceFactory
import java.io.FileNotFoundException
import java.util.*

class YamlPropertySourceFactory : PropertySourceFactory {

    override fun createPropertySource(name: String?, resource: EncodedResource): PropertySource<*> {
        val propertiesFromYaml: Properties = loadYamlIntoProperties(resource)
        val sourceName = name?: resource.resource.filename
        return PropertiesPropertySource(sourceName!!, propertiesFromYaml)
    }

    private fun loadYamlIntoProperties(resource: EncodedResource): Properties = try {
        val factory = YamlPropertiesFactoryBean()
        factory.setResources(resource.resource)
        factory.afterPropertiesSet()
        factory.`object` ?: throw IllegalStateException("properties is not set")
    } catch (e: IllegalStateException) {
        when (e.cause) {
            is FileNotFoundException -> throw  e.cause as FileNotFoundException
            else -> throw e
        }
    }
}
