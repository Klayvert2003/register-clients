package io.github.klayvert2003.clients.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ConvertIconToBytesArray {
    public static byte[] convert(Icon icon) {
        if (icon == null) {
            return null;
        }

        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconWidth(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2D = bufferedImage.createGraphics();
        icon.paintIcon(null, g2D, 0, 0);
        g2D.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return null;
        }

        return baos.toByteArray();
    }
}
