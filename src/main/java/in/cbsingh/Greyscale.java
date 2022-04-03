package in.cbsingh;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Greyscale {

    public static void main(String[] args) {
        BufferedImage image = null;
        File file = null;

        URL resource = Greyscale.class.getClassLoader().getResource("dummy.jpg");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        try {
            file = new File(resource.toURI());
            image = ImageIO.read(file);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e);
        }

        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int imageRGB = image.getRGB(x, y);
                int alpha = (imageRGB >> 24) & 0xff;
                int red = (imageRGB >> 16) & 0xff;
                int green = (imageRGB >> 8) & 0xff;
                int blue = imageRGB & 0xff;
                int avg = (red + green + blue) / 3;
                imageRGB = (alpha << 24) | (avg << 16) | (avg << 8) | avg;
                image.setRGB(x, y, imageRGB);
            }
        }
        try {
            file = new File("greyscale.png");
            ImageIO.write(image, "png", file);
            System.out.println("Converted Colored image to a grayscale image");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
