package softwareproblems.week4.greyscale;

import edu.duke.*;

import java.io.File;

public class BatchInversions {

    public ImageResource makeInversion(ImageResource inputImage){

        ImageResource outputImage = new ImageResource(inputImage.getWidth(), inputImage.getHeight());
        for(Pixel pixel : outputImage.pixels()){
            Pixel inPixel = inputImage.getPixel(pixel.getX(),pixel.getY());
            int inputRed = inPixel.getRed();
            int inputGreen = inPixel.getGreen();
            int inputBlue = inPixel.getBlue();
            pixel.setRed(255-inputRed);
            pixel.setGreen(255-inputGreen);
            pixel.setBlue(255-inputBlue);
        }
        return outputImage;
    }

    public void selectAndConvert(){
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file : directoryResource.selectedFiles()){
            ImageResource inputImageFile = new ImageResource(file);
            ImageResource invertedImageFile = makeInversion(inputImageFile);
            String inputFileName = inputImageFile.getFileName();
            String savedFileName = "inverted-"+inputFileName;
            invertedImageFile.setFileName(savedFileName);
            invertedImageFile.draw();
            invertedImageFile.save();
        }
    }
}
