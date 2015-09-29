
public class NeuralLayer {

	protected Neuron[] neurons;
	
	protected NeuralLayer(){
		
	}
	
	public NeuralLayer(int size){
		neurons = new Neuron[size];
		for(int i=0; i<neurons.length; i++){
			neurons[i] = new Neuron();
		}
	}

	public Neuron[] getNeurons() {
		return neurons;
	}
	
	public void train(){
		for(int i=0; i<neurons.length; i++){
			neurons[i].calculateError();
			neurons[i].adjustWeights();
		}
	}

	public void updateOutputs() {
		for(int i=0; i<neurons.length; i++){	neurons[i].updateOutput();	}		
	}
	
	public Neuron getNeuron(int neuronIndex){
		if(neuronIndex < neurons.length){
			return neurons[neuronIndex];
		}
		return null;
	}
}
