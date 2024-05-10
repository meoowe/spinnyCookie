import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.tween.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.input.*
import korlibs.korge.view.align.*
import korlibs.math.geom.*
import korlibs.math.interpolation.*

suspend fun main() = Korge(windowSize = Size(512, 512), backgroundColor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo { MyScene() }
}

class MyScene : Scene() {
    private suspend fun wait(ms: TimeSpan) {
        delay(ms)
    }

    override suspend fun SContainer.sceneMain() {
        val image = image(resourcesVfs["cookie.png"].readBitmap()) {
            anchor(.5, .5)
            scale(0.8)
            position(256, 256)
        }
        var cookies = 0
        var rotation = 1.degrees
        val cookie = image(resourcesVfs["cookie.png"].readBitmap()) {
            stage?.let { alignTopToTopOf(it) }
            stage?.let { alignLeftToLeftOf(it) }
            scale(0.05)
        }
        val cookieDisplay = text(" = $cookies") {
            alignLeftToRightOf(cookie)
        }//hi
        image.onClick { rotation += 1.degrees; cookies += 1 ; cookieDisplay.text = " = $cookies"}
        while (true) {
            wait(15.milliseconds)
            image.rotation += rotation
        }
    }
}
