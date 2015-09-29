import java.util.ArrayList;
import java.util.Random;


public class Main {

	private static double[][] totalInputData = {{0,1},{1,0}};
	private static double[][] totalDesiredOutput = {{1,0},{0,1}};
	private static double[][] testData = { {0,1}, {1,0}};
	
	static final int TOTALEPOCHS = 1000;
	
	public static void main(String[] args){
		NeuralNetwork NN = new NeuralNetwork(2, 2);
		int i = 0;
		
		for(int f=0; f<testData.length; f++){
			NN.displayOutputForInput(testData[f]);
		}
	
		while(i<TOTALEPOCHS){
			NN.trainFromData(totalInputData, totalDesiredOutput );
			System.out.print("epochs : ");
			System.out.println(i);
			i++;
		}
		for(int f=0; f<testData.length; f++){
				NN.displayOutputForInput(testData[f]);
		}
		System.out.println("So far seems good. Keep IT UP!!");
		
		NeuralLayer outputLayer = NN.getLayer(1);
		Neuron outputNeuron1 = outputLayer.getNeuron(0);
		Neuron outputNeuron2 = outputLayer.getNeuron(1);
		
		for(int u=0; u<outputNeuron1.inputNeuronWeights.length; u++){
			System.out.println(outputNeuron1.inputNeuronWeights[u]);
			System.out.println(outputNeuron1.inputNeuronWeights[u]);
		}
		
		
		System.out.println(1/(1+Math.exp(-3.73)));
		
	}
	
	
	
}
