import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.tween.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.input.*
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
        var rotation = 1.degrees
        image.onClick { rotation = 4.degrees }
		while (true) {
            wait(15.milliseconds)
            image.rotation += rotation
		}
	}
}
