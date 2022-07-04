package screens.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

/*
    Image from url
 */
@Composable
fun ComposableImage(
    link: String
) {
    val url = URL(link)
    val connection = url.openConnection() as HttpURLConnection
    connection.connect()

    val inputStream = connection.inputStream
    val bufferedImage = ImageIO.read(inputStream)

    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "jpg", stream)
    val byteArray = stream.toByteArray()

    val bitmap: ImageBitmap = Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    Image(bitmap, null)
}
