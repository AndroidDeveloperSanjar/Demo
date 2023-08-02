import com.android.build.api.dsl.BaseFlavor
import com.android.build.api.dsl.VariantDimension
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset
import java.util.Enumeration
import java.util.Properties

fun Project.getVersionName(): String {
    val versionPropsFile = file("version.properties")
    if (versionPropsFile.canRead()) {
        val versionProps = Properties()
        FileInputStream(versionPropsFile).use {
            versionProps.load(it)
        }
        val versionMajor = versionProps.getProperty("VERSION_MAJOR").toInt()
        val versionMinor = versionProps.getProperty("VERSION_MINOR").toInt()
        val versionPatch = versionProps.getProperty("VERSION_PATCH").toInt()
        return "v$versionMajor.$versionMinor.$versionPatch"
    } else {
        throw GradleException("Could not read version.properties!")
    }
}

fun Project.getNewVersionCode(): Int {
    val newVersionCode: Int
    val versionPropsFile = file("version.properties")
    if (versionPropsFile.canRead()) {
        val versionProps = LinkedProperties()
        FileInputStream(versionPropsFile).use {
            versionProps.load(it)
        }
        val oldVersionCode = versionProps.getProperty("VERSION_CODE").toInt()
        var value = 0
        gradle.startParameter.taskNames.forEach {
            if (it.contains("prod", true)) {
                value = 1
            }
        }
        newVersionCode = oldVersionCode + value
        if (oldVersionCode != newVersionCode) {
            versionProps.setProperty("VERSION_CODE", newVersionCode.toString())
            val arrayOut = ByteArrayOutputStream()
            versionProps.store(arrayOut, null)
            val string = String(arrayOut.toByteArray())
            val sep = System.getProperty("line.separator")
            val out = FileOutputStream(versionPropsFile.toString())
            out.use {
                it.write(
                    string.substring(string.indexOf(sep) + sep.length)
                        .toByteArray(Charset.defaultCharset())
                )
            }
        }
    } else {
        throw GradleException("Could not read version.properties!")
    }
    return newVersionCode
}

class LinkedProperties : Properties() {
    private val linkMap: MutableMap<Any, Any> = LinkedHashMap()
    override val entries: MutableSet<MutableMap.MutableEntry<Any, Any>>
        get() = linkMap.entries
    override val size: Int
        get() = linkMap.size
    override val keys: MutableSet<Any>
        get() = linkMap.keys
    override val values: MutableCollection<Any>
        get() = linkMap.values

    @Synchronized
    override fun put(key: Any, value: Any): Any? {
        return linkMap.put(key, value)
    }

    override fun getProperty(key: String?): String {
        return linkMap[key as Any].toString()
    }

    override fun putAll(from: Map<*, *>) {
        linkMap.putAll(from as Map<out Any, Any>)
    }

    override fun get(key: Any?): Any? {
        return linkMap.get(key)
    }

    override fun remove(key: Any?): Any? {
        return linkMap.remove(key)
    }

    @Synchronized
    override fun contains(value: Any): Boolean {
        return linkMap.containsValue(value)
    }

    @Synchronized
    override fun containsKey(key: Any): Boolean {
        return linkMap.containsKey(key)
    }

    override fun containsValue(value: Any): Boolean {
        return linkMap.containsValue(value)
    }

    @Synchronized
    override fun elements(): Enumeration<Any> {
        throw UnsupportedOperationException(
            "Enumerations are so old-school, don't use them, " +
                    "use keySet() or entrySet() instead"
        )
    }

    @Synchronized
    override fun clear() {
        linkMap.clear()
    }

    override fun toString(): String {
        return linkMap.toString()
    }

    override fun isEmpty(): Boolean {
        return linkMap.isEmpty()
    }
}

fun BaseFlavor.buildConfigInt(name: String, value: Int) =
    this.buildConfigField("int", name, value.toString())

fun BaseFlavor.buildConfigLong(name: String, value: Long) =
    this.buildConfigField("long", name, value.toString())

fun BaseFlavor.buildConfigBool(name: String, value: Boolean) =
    this.buildConfigField("boolean", name, value.toString())

fun BaseFlavor.buildConfigString(name: String, value: String) =
    this.buildConfigField("String", name, "\"$value\"")

fun VariantDimension.buildConfigInt(name: String, value: Int) =
    this.buildConfigField("int", name, value.toString())

fun VariantDimension.buildConfigLong(name: String, value: Long) =
    this.buildConfigField("long", name, value.toString())

fun VariantDimension.buildConfigBool(name: String, value: Boolean) =
    this.buildConfigField("boolean", name, value.toString())

fun VariantDimension.buildConfigString(name: String, value: String) =
    this.buildConfigField("String", name, "\"$value\"")

internal fun DependencyHandler.implementation(dependencyNotation: Any) =
    add("implementation", dependencyNotation)

internal fun DependencyHandler.debugImplementation(dependencyNotation: Any) =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.qaImplementation(dependencyNotation: Any) =
    add("qaImplementation", dependencyNotation)

fun DependencyHandler.releaseImplementation(dependencyNotation: Any) =
    add("releaseImplementation", dependencyNotation)

internal fun DependencyHandler.testImplementation(dependencyNotation: Any) =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) =
    add("androidTestImplementation", dependencyNotation)

internal fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any) =
    add("testRuntimeOnly", dependencyNotation)

internal fun DependencyHandler.androidTestRuntimeOnly(dependencyNotation: Any) =
    add("androidTestRuntimeOnly", dependencyNotation)

internal fun DependencyHandler.compileOnly(dependencyNotation: Any) =
    add("compileOnly", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any) =
    add("kapt", dependencyNotation)

internal fun DependencyHandler.api(dependencyNotation: Any) =
    add("api", dependencyNotation)
