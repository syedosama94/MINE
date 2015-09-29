import java.beans.DesignMode;


public class InputLayer extends NeuralLayer{

	double[] inputData;
	
	InputLayer(int size) {
		neurons = new Neuron[size];
		for(int i=0; i<neurons.length; i++){	instantiateNeuron(i);	}
	}

	private void instantiateNeuron(final int i){
		neurons[i] = new Neuron(){

			@Override
			void updateOutput() {	output = inputData[i];	}
			
		};		
	}
	
	void setInputData(double[] inputData) {	this.inputData = inputData;	}
	
}
