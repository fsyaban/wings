import org.gradle.api.artifacts.dsl.DependencyHandler
import dependancies.*

fun DependencyHandler.libraries() {

    androidX()
    DaggerHilt()
    glide()
    gson()
    materialDesign()
    NavGraph()
    okHttp()
    retrofit()
    vmLifeCycle()
    testUnit()
    paging()
    room()
}