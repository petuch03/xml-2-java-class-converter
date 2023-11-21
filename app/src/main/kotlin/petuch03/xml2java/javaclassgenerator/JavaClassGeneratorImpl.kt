package petuch03.xml2java.javaclassgenerator

import petuch03.xml2java.utils.JavaClassPrintable
import petuch03.xml2java.utils.XMLExtensionElementModel

class JavaClassGeneratorImpl : JavaClassGenerator {
    override fun identifyType(value: String?): String {
        if (value.isNullOrEmpty()) {
            return "String"
        }
        return if (value.toIntOrNull() != null) "int" else "String"
    }

    override fun generateJavaClass(extensionElement: XMLExtensionElementModel): JavaClassPrintable {
        val className = extensionElement.type
        val properties = extensionElement.contents

        val fields = generateJavaFields(properties)
        val methods = generateJavaMethods(properties)

        val printable =
            """
class $className {

$fields


$methods
}
""".trimIndent()

        return JavaClassPrintable(className, printable)
    }


    private fun generateJavaMethods(properties: Map<String, String?>): String {
        fun generateGetter(key: String, type: String): String {
            return "\tpublic $type get${key.capitalize()}() {\n\t\treturn $key;\n\t}\n\n"
        }

        fun generateSetter(key: String, type: String): String {
            return "\tpublic void set${key.capitalize()}($type $key) {\n\t\tthis.$key = $key;\n\t}\n\n"
        }

        return properties.map { (key, value) ->
            val type = identifyType(value)
            generateGetter(key, type) + generateSetter(key, type)
        }.joinToString("").removeSuffix("\n\n")
    }

    private fun generateJavaFields(properties: Map<String, String?>): String {
        return properties.map { (key, value) ->
            "\tprivate ${identifyType(value)} $key;"
        }.joinToString("\n")
    }

    private fun String.addPrefix(prefix: String, ignoreEmpty: Boolean = true): String {
        if (this.isEmpty() && ignoreEmpty) return this
        else if (this.startsWith(prefix)) return this
        return "$prefix$this"
    }
}