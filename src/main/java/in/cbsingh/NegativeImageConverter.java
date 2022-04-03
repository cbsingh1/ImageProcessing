package in.cbsingh;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class NegativeImageConverter {

    public static void main(String args[])throws IOException
    {
        BufferedImage image = null;
        File file = null;

        URL resource = Greyscale.class.getClassLoader().getResource("dummy.jpg");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        try
        {
            file = new     File(resource.toURI());
            image = ImageIO.read(file);
        }
        catch(IOException | URISyntaxException e)
        {
            System.out.println(e);
        }
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x,y);
                int alpha = (rgb>>24) & 0xff;
                int red = (rgb>>16) & 0xff;
                int green = (rgb>>8) & 0xff;
                int blue = rgb & 0xff;
                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;
                rgb = (alpha<<24) | (red<<16) | (green<<8) | blue;
                image.setRGB(x, y, rgb);
            }
        }
        try
        {
            file = new File("negative.jpg");
            ImageIO.write(image, "jpg", file);
            System.out.println("Successfully converted a colored image into a negative image");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

}
