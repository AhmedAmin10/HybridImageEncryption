/*
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

class HybridImageEncryption {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) {
        try {
            SecretKey key = generateAESKey(); // Generate AES key
            File inputFile = new File("C:\\Users\\Ahmed\\Desktop\\images\\myphoto.jpg");
            File encryptedFile = new File("C:\\Users\\Ahmed\\Desktop\\images\\myphotoEncrypted.jpg");

            encryptImage(inputFile, encryptedFile, key);
            System.out.println("Image encrypted successfully.");
        } catch (Exception e) {
            System.err.println("Error during encryption: " + e.getMessage());
        }
    }

    private static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // You can use 128, 192, or 256 depending on the key size you need
        return keyGen.generateKey();
    }

    private static void encryptImage(File inputFile, File outputFile, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        BufferedImage originalImage = ImageIO.read(inputFile);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int[] pixels = originalImage.getRGB(0, 0, width, height, null, 0, width);

        // Apply chaotic map for additional encryption
        chaoticMap(pixels, width, height);

        // Convert pixels array to byte array
        byte[] bytePixels = new byte[pixels.length * 3];
        for (int i = 0; i < pixels.length; i++) {
            bytePixels[i * 3] = (byte) ((pixels[i] >> 16) & 0xFF);
            bytePixels[i * 3 + 1] = (byte) ((pixels[i] >> 8) & 0xFF);
            bytePixels[i * 3 + 2] = (byte) (pixels[i] & 0xFF);
        }

        // Encrypt the byte array using AES
        byte[] encryptedPixels = cipher.doFinal(bytePixels);

        // Convert the encrypted byte array back to int array
        int[] encryptedIntPixels = new int[encryptedPixels.length / 3];
        for (int i = 0; i < encryptedIntPixels.length; i++) {
            int r = encryptedPixels[i * 3] & 0xFF;
            int g = encryptedPixels[i * 3 + 1] & 0xFF;
            int b = encryptedPixels[i * 3 + 2] & 0xFF;
            encryptedIntPixels[i] = (r << 16) | (g << 8) | b;
        }

        // Create a new image from the encrypted pixels
        BufferedImage encryptedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        encryptedImage.setRGB(0, 0, width, height, encryptedIntPixels, 0, width);

        // Save the encrypted image
        ImageIO.write(encryptedImage, "jpg", outputFile);
    }

    private static void chaoticMap(int[] pixels, int width, int height) {
        Random random = new Random();
        double x = random.nextDouble();
        double y = random.nextDouble();

        for (int i = 0; i < pixels.length; i++) {
            x = 4.0 * x * (1 - x); // Logistic map equation
            y = 4.0 * y * (1 - y); // Logistic map equation

            // XOR pixel values with chaotic map values
            pixels[i] = pixels[i] ^ ((int) (x * 255) << 16 | (int) (y * 255) << 8 | (int) (x * y * 255));
        }
    }
}
*/

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

class HybridImageEncryption {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the image: ");
        String imagePath = scanner.nextLine();

        try {
            String encryptedImagePath = encryptImage(imagePath);
            System.out.println("Image encrypted successfully. Encrypted image saved at: " + encryptedImagePath);
        } catch (Exception e) {
            System.err.println("Error during encryption: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static String encryptImage(String imagePath) throws Exception {
        SecretKey key = generateAESKey(); // Generate AES key
        File inputFile = new File(imagePath);

        // Ensure the input file exists
        if (!inputFile.exists()) {
            throw new IOException("Input file not found: " + imagePath);
        }

        String outputFileName =  "Encrypted_" + inputFile.getName();
        File outputFile = new File(inputFile.getParent(), outputFileName);

        encryptImage(inputFile, outputFile, key);

        return outputFile.getAbsolutePath();
    }

    private static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // You can use 128, 192, or 256 depending on the key size you need
        return keyGen.generateKey();
    }

    private static void encryptImage(File inputFile, File outputFile, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        BufferedImage originalImage = ImageIO.read(inputFile);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int[] pixels = originalImage.getRGB(0, 0, width, height, null, 0, width);

        // Apply chaotic map for additional encryption
        chaoticMap(pixels, width, height);

        // Convert pixels array to byte array
        byte[] bytePixels = new byte[pixels.length * 3];
        for (int i = 0; i < pixels.length; i++) {
            bytePixels[i * 3] = (byte) ((pixels[i] >> 16) & 0xFF);
            bytePixels[i * 3 + 1] = (byte) ((pixels[i] >> 8) & 0xFF);
            bytePixels[i * 3 + 2] = (byte) (pixels[i] & 0xFF);
        }

        // Encrypt the byte array using AES
        byte[] encryptedPixels = cipher.doFinal(bytePixels);

        // Convert the encrypted byte array back to int array
        int[] encryptedIntPixels = new int[encryptedPixels.length / 3];
        for (int i = 0; i < encryptedIntPixels.length; i++) {
            int r = encryptedPixels[i * 3] & 0xFF;
            int g = encryptedPixels[i * 3 + 1] & 0xFF;
            int b = encryptedPixels[i * 3 + 2] & 0xFF;
            encryptedIntPixels[i] = (r << 16) | (g << 8) | b;
        }

        // Create a new image from the encrypted pixels
        BufferedImage encryptedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        encryptedImage.setRGB(0, 0, width, height, encryptedIntPixels, 0, width);

        // Save the encrypted image
        ImageIO.write(encryptedImage, "jpg", outputFile);
    }

    private static void chaoticMap(int[] pixels, int width, int height) {
        Random random = new Random();
        double x = random.nextDouble();
        double y = random.nextDouble();

        for (int i = 0; i < pixels.length; i++) {
            x = 4.0 * x * (1 - x); // Logistic map equation
            y = 4.0 * y * (1 - y); // Logistic map equation

            // XOR pixel values with chaotic map values
            pixels[i] = pixels[i] ^ ((int) (x * 255) << 16 | (int) (y * 255) << 8 | (int) (x * y * 255));
        }
    }
}
