@file:Suppress("LeakingThis")

package xyz.wagyourtail.jvmdg.gradle.task

import groovy.lang.Closure
import groovy.lang.DelegatesTo
import org.gradle.api.JavaVersion
import org.gradle.api.file.FileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.jvm.tasks.Jar
import org.gradle.process.JavaExecSpec
import org.jetbrains.annotations.ApiStatus
import xyz.wagyourtail.jvmdg.ClassDowngrader
import xyz.wagyourtail.jvmdg.cli.Flags
import xyz.wagyourtail.jvmdg.compile.ZipDowngrader
import xyz.wagyourtail.jvmdg.gradle.JVMDowngraderExtension
import xyz.wagyourtail.jvmdg.util.*
import java.nio.file.StandardOpenOption
import kotlin.io.path.outputStream

abstract class DowngradeJar : Jar() {

    private val jvmdg by lazy {
        project.extensions.getByType(JVMDowngraderExtension::class.java)
    }

    @get:Input
    @get:Optional
    var downgradeTo by FinalizeOnRead(JavaVersion.VERSION_1_8)


    @get:InputFiles
    @get:Optional
    var classpath: FileCollection by FinalizeOnRead(LazyMutable {
        project.extensions.getByType(SourceSetContainer::class.java).getByName("main").compileClasspath
    })

    @get:Input
    @get:Optional
    @get:ApiStatus.Experimental
    abstract val debugSkipStubs: ListProperty<Int>

    @get:Input
    @get:Optional
    @get:ApiStatus.Experimental
    abstract val debugPrint: Property<Boolean>

    @get:InputFile
    abstract val inputFile: RegularFileProperty

    init {
        group = "JVMDowngrader"
        description = "Downgrades the jar to the specified version"

        debugSkipStubs.convention(mutableListOf())
        debugPrint.convention(false)
    }

    private var configureDowngrade: JavaExecSpec.() -> Unit = {}

    fun configureDowngrade(spec: JavaExecSpec.() -> Unit) {
        val old = configureDowngrade
        configureDowngrade = {
            old()
            spec()
        }
    }

    fun configureDowngrade(
        @DelegatesTo(
            JavaExecSpec::class,
            strategy = Closure.DELEGATE_FIRST
        )
        closure: Closure<*>
    ) {
        configureDowngrade {
            closure.delegate = this
            closure.call(this)
        }
    }

    @TaskAction
    fun doDowngrade() {
        val tempOutput = temporaryDir.resolve("downgradedInput.jar")
        tempOutput.deleteIfExists()

        val flags = Flags()
        flags.api = jvmdg.apiJar
        flags.printDebug = debugPrint.get()
        flags.classVersion = downgradeTo.toOpcode()
        flags.debugSkipStubs = debugSkipStubs.get().toSet()
        ClassDowngrader.downgradeTo(flags).use {
            ZipDowngrader.downgradeZip(
                it,
                inputFile.asFile.get().toPath(),
                classpath.files.map { it.toURI().toURL() }.toSet(),
                tempOutput.toPath()
            )
        }

        inputFile.asFile.get().toPath().readZipInputStreamFor("META-INF/MANIFEST.MF", false) { inp ->
            // write to temp file
            val inpTmp = temporaryDir.toPath().resolve("input-manifest.MF")
            inpTmp.outputStream(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING).use { out ->
                inp.copyTo(out)
            }
            this.manifest {
                it.from(inpTmp)
            }
        }

        from(project.zipTree(tempOutput))
        copy()
    }

}
