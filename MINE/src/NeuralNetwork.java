import java.io.File;
/*
 * 
 * Harami-ud-dehar qisam ka project baney ga yeh!!
 * 
 * 
 * 
 */

public class NeuralNetwork {

	NeuralLayer[] neuralLayers;
	double[] inputData , desiredOutput;
	
	public NeuralNetwork(int...sizes){
		neuralLayers = new NeuralLayer[sizes.length];
		neuralLayers[0] = new InputLayer(sizes[0]);
		for(int i=1; i<sizes.length-1; i++){
			neuralLayers[i] = new NeuralLayer(sizes[i]);
		}
		neuralLayers[sizes.length-1] = new OutputLayer(sizes[sizes.length-1]);
		
		connectLayers();
	}
	
	
//	void setTrainingFile(String pathToFile){
//		FileHandler fh = new FileHandler();
//		fh.setTraingFile(pathToFile);
//		setInputData(fh.getDataFromInputFile());
//		setDesiredOutput(fh.getDataFromDesiredOutputFile);
//	}
	
	private void connectLayers(){
		NeuralLayer previousLayer = neuralLayers[0] , nextLayer = null;
		Neuron[] previousLayerNeurons = previousLayer.getNeurons() , nextLayerNeurons = null;
		
		//this is required for forward pass
		for(int i = 1; i<neuralLayers.length; i++){
			nextLayer = neuralLayers[i];
			nextLayerNeurons = nextLayer.getNeurons();
			for(int j=0; j<nextLayerNeurons.length; j++){
				nextLayerNeurons[j].setInputNeurons(previousLayerNeurons);
			}
			previousLayer = nextLayer;
			previousLayerNeurons = previousLayer.getNeurons();
		}
		
		//this is required for back propagation
		NeuralLayer currentLayer = null;
		Neuron[] currentLayerNeurons = null;
		for(int i = 0; i<neuralLayers.length - 1; i++){
			currentLayer = neuralLayers[i];
			currentLayerNeurons = currentLayer.getNeurons();
			for(int j=0; j<currentLayerNeurons.length; j++){
				currentLayerNeurons[j].setOutputNeurons(neuralLayers[i+1].getNeurons());
			}
		}
	}
	
	void trainFromFiles(String inputFilePath , String outputFilePath){
		
	}
	
	void trainFromData(double[][] totalInputData , double[][] totalDesiredOutput , int EPOCHS){
		for(int i=0; i<EPOCHS; i++){   trainFromData(totalInputData , totalDesiredOutput);	}
	}
	
	void trainFromData(double[][] totalInputData , double[][] totalDesiredOutput){
		for(int i=0; i<totalInputData.length; i++){
			inputData = totalInputData[i];
			desiredOutput = totalDesiredOutput[i];
			train();
		}
	}
	
	private void train(){
			Thread t = new Thread();
				
			forwardPass();
			backwardPass();		
	}
	
	
	private void forwardPass(){
		InputLayer inputLayer = (InputLayer) neuralLayers[0];
		inputLayer.setInputData(inputData);
		inputLayer.updateOutputs();
		
		for(int i = 1; i<neuralLayers.length; i++){	neuralLayers[i].updateOutputs();	}		
	}
	
	private void backwardPass(){
		OutputLayer outputLayer = (OutputLayer) neuralLayers[neuralLayers.length - 1];
		outputLayer.setDesiredOutput(desiredOutput);
		outputLayer.train();
		
		for(int i=neuralLayers.length - 2; i>0; i--){	neuralLayers[i].train();	}
	}

	public void displayOutputForInput(double[] input){
		inputData = input;
		forwardPass();
		Neuron[] outputNeurons = neuralLayers[neuralLayers.length-1].getNeurons();
		System.out.println(String.format("pakra gaya , %d" , outputNeurons.length));
		for(int i=0; i<outputNeurons.length; i++){
			System.out.println(outputNeurons[i].getOutput());
		}
	}
	
	public NeuralLayer getLayer(int layerIndex){
		if(layerIndex < neuralLayers.length){
			return neuralLayers[layerIndex];
		}
		return null;
	}
	
}
