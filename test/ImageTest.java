
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Description ImageTest
 * @Author Radish
 * @Date 2020-08-29 08:34
 */
public class ImageTest {
    @Test
    void test() {
        try {
            BufferedImage image = ImageIO.read(new File("F:/图片/by.jpeg"));
            assertNotNull(image);
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bullets/by.jpeg"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
