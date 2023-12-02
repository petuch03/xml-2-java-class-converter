package petuch03.xml2java.javaclassgenerator

import petuch03.xml2java.utils.JavaClassPrintable
import petuch03.xml2java.utils.JavaType
import petuch03.xml2java.utils.XMLExtensionElementModel
import java.util.*

class JavaClassGeneratorImpl : JavaClassGenerator {
    override fun identifyType(value: String?): JavaType {
        requireNotNull(value) { return JavaType.STRING }
        return if (isInt(value)) JavaType.INT
        else JavaType.STRING
    }

    override fun generateJavaClass(extensionElement: XMLExtensionElementModel): JavaClassPrintable {
        val className = extensionElement.type
        val properties = extensionElement.contents

        val fields = generateJavaFields(properties)
        val methods = generateJavaMethods(properties)

        val printable = "class $className {\n\n$fields\n\n\n$methods\n}\n"

        return JavaClassPrintable(className, printable)
    }


    private fun generateJavaMethods(properties: Map<String, String?>): String {
        fun generateGetter(key: String, type: JavaType): String {
            return "\tpublic $type get${key.capitalizeFirst()}() {\n\t\treturn $key;\n\t}\n\n"
        }

        fun generateSetter(key: String, type: JavaType): String {
            return "\tpublic void set${key.capitalizeFirst()}($type $key) {\n\t\tthis.$key = $key;\n\t}\n\n"
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

    private fun String.capitalizeFirst(): String {
        return if (this.isEmpty()) this
        else this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    private fun isInt(value: String): Boolean {
        return value.toIntOrNull() != null
    }

    // TODO unclear how to detect float
    @Suppress("unused")
    private fun isFloat(value: String): Boolean {
        val correctSeparatorFlag = value.count{it == '.' || it == ','} == 1
        val correctNumbersFlag = value.filter { it.isDigit() }.length == value.length - 1
        return correctNumbersFlag && correctSeparatorFlag
    }
}