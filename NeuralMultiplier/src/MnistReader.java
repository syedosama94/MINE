import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class implements a reader for the MNIST dataset of handwritten digits. The dataset is found
 * at http://yann.lecun.com/exdb/mnist/.
 * 
 * @author Gabe Johnson <johnsogg@cmu.edu>
 */
public class MnistReader {

  /**
   * @param args
   *          args[0]: label file; args[1]: data file.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
	NeuralNetwork NN = new NeuralNetwork(400 , 100 , 10); 
	int[][][] sampleImages = new int[10][][];  
	  
    DataInputStream labels = new DataInputStream(new FileInputStream("C:\\Users\\OWNER\\Desktop\\OCR\\train-labels.idx1-ubyte"));
    DataInputStream images = new DataInputStream(new FileInputStream("C:\\Users\\OWNER\\Desktop\\OCR\\train-images.idx3-ubyte"));
    int magicNumber = labels.readInt();
    if (magicNumber != 2049) {
      System.err.println("Label file has wrong magic number: " + magicNumber + " (should be 2049)");
      System.exit(0);
    }
    magicNumber = images.readInt();
    if (magicNumber != 2051) {
      System.err.println("Image file has wrong magic number: " + magicNumber + " (should be 2051)");
      System.exit(0);
    }
    int numLabels = labels.readInt();
    int numImages = images.readInt();
    int numRows = images.readInt();
    int numCols = images.readInt();
    if (numLabels != numImages) {
      System.err.println("Image file and label file do not contain the same number of entries.");
      System.err.println("  Label file contains: " + numLabels);
      System.err.println("  Image file contains: " + numImages);
      System.exit(0);
    }

    long start = System.currentTimeMillis();
    int numLabelsRead = 0;
    int numImagesRead = 0;
    int i=0;//////////////////////
    while (labels.available() > 0 && numLabelsRead < numLabels && /*****/i<10) {
     	byte label = labels.readByte();
      numLabelsRead++;
      int[][] image = new int[numCols][numRows];
      for (int colIdx = 0; colIdx < numCols; colIdx++) {
        for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
          image[colIdx][rowIdx] = images.readUnsignedByte();
          
        }
      }
      numImagesRead++;
//      new DrawImage(image);
      sampleImages[i] = image;
      i++;////////////////////
      // At this point, 'label' and 'image' agree and you can do whatever you like with them.

      if (numLabelsRead % 10 == 0) {
        System.out.print(".");
      }
      if ((numLabelsRead % 800) == 0) {
        System.out.print(" " + numLabelsRead + " / " + numLabels);
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        long minutes = elapsed / (1000 * 60);
        long seconds = (elapsed / 1000) - (minutes * 60);
        System.out.println("  " + minutes + " m " + seconds + " s ");
      }
    }
    
    //converting each of the the 20 x 20 two dimensional matrix in sampleImages to a size 400 matrix
    double[][] totalDesiredInput = new double[10][400];
    int pixelIndex = 0;
    for(int f=0; f<10; f++){
    	pixelIndex = 0;
    	int row=0;
    	int column =0;
    	for(int u=0; u<400; u++){
    	    if(u%19 == 0 && u!=0){
    	    	row++; column = 0;
    	    }
    		totalDesiredInput[f][u] = sampleImages[f][row][column];
    		column++;
    	}
    }
    
    double[][] totalDesiredOutput = {{1,0,1,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0,0},
    		{0,0,1,0,0,0,0,0,0,0},
    		{1,0,0,0,0,0,0,0,0,0},
    		{1,0,0,1,0,0,0,0,0,0},
    		{0,1,0,0,0,0,0,0,0,0},
    		{1,0,0,0,0,0,0,0,0,0},
    		{1,1,0,0,0,0,0,0,0,0},
    		{1,0,0,0,0,0,0,0,0,0},
    		{0,0,1,0,0,0,0,0,0,0}
			};
    
    final int TOTALEPOCHS = 1000;
    for (int epochs=0; epochs<TOTALEPOCHS; epochs++){
        NN.trainFromData(totalDesiredInput, totalDesiredOutput);
        System.out.print("epochs : ");
		System.out.println(epochs);
		
    }
    
    System.out.println();
    long end = System.currentTimeMillis();
    long elapsed = end - start;
    long minutes = elapsed / (1000 * 60);
    long seconds = (elapsed / 1000) - (minutes * 60);
    System.out
        .println("Read " + numLabelsRead + " samples in " + minutes + " m " + seconds + " s ");
  }

}