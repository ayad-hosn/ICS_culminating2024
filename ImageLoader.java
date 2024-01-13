import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageLoader {

    
    public void openImage() throws Exception {
        File file = new File("./Graph.png");
        BufferedImage image = ImageIO.read(file);
    }
}
