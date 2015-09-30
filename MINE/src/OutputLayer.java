
public class OutputLayer extends NeuralLayer{

	double[] desiredOutput;
	
	OutputLayer(int size) {
		neurons = new Neuron[size];
		for(int i=0; i<neurons.length; i++){	instantiateNeuron(i);	}
	}

	private void instantiateNeuron(final int i){
		neurons[i] = new Neuron(){
			
			@Override
			void calculateError(){	error = output*(1-output)*(desiredOutput[i] - output);	}
			
		};		
	}
	
	public void setDesiredOutput(double[] desiredOutput) {	this.desiredOutput = desiredOutput;	}

}
