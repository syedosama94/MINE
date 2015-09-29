import java.util.Random;


public class Neuron {

	Neuron[] inputNeurons , outputNeurons;
	Double[] inputNeuronWeights;
	protected double output;
	protected double error;
	
	void updateOutput(){
		this.output = 0;
		for(int i=0; i<inputNeurons.length; i++){	
			this.output += inputNeurons[i].getOutput()*inputNeuronWeights[i];	
		}
		this.output = 1/(1+Math.exp(-1*this.output));	
	}
	
	void calculateError(){
		double errorWeightProductSum = 0;
		for(int i=0; i<outputNeurons.length; i++){
			errorWeightProductSum += outputNeurons[i].getError()*outputNeurons[i].getInputNeuronWeight(this);
		}
		
		error = output*(1-output)*errorWeightProductSum;
	}

	void adjustWeights(){
		for(int i=0; i<inputNeuronWeights.length; i++){		inputNeuronWeights[i] += error*inputNeurons[i].getOutput();		}
	}
	
	void setInputNeurons(Neuron[] neurons) {
		inputNeurons = neurons;
		inputNeuronWeights = new Double[neurons.length];
		for(int i=0; i<inputNeuronWeights.length; i++){		inputNeuronWeights[i] = new Random().nextDouble();		}
	}

	private double getInputNeuronWeight(Neuron neuron) {
		for(int i=0; i<inputNeurons.length; i++){
			if(inputNeurons[i] == neuron){	return inputNeuronWeights[i];	}
		}
		return 0;
	}

	public double getOutput(){return output;}

	private double getError() {return error;}

	public void setOutput(double d) {output = d;}
	
	public void setOutputNeurons(Neuron[] neurons) {outputNeurons = neurons;}
	
		
}
