import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageLoader {
    public static void main(String[] args) throws Exception {
        File file = new File("untitled.png");
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Image dimensions: " + width + " x " + height);
    }
}
