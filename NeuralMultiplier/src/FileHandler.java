import java.io.File;


public class FileHandler {
	File inputFile , resultFile , desiredOutputFile;
	
	public double[][] getInputFromTrainingFile() {
		return new double[5][5];
	}

	public Object getInputFromTestFile() {

		return null;
	}

	void setInputFile(String pathToFile){

		File f = new File(pathToFile);
		if(f.exists() && f.isFile()){
			if(fileFormatLegal( pathToFile )){
				if(fileContentLegal( pathToFile )){
					inputFile = new File(pathToFile);//everything seems fine
				}else{/* throw exception*/}
			}else{/* throw exception*/}
		}else{/*throw exception*/}
	}

	private String getFileExtension(String pathToFile) {
        File file = new File(pathToFile);
		String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	boolean fileFormatLegal(String pathToFile){	return 	getFileExtension(pathToFile).equalsIgnoreCase("txt");	}
	
	boolean fileContentLegal(String pathToFile){
		return true;
	}
	
	
}
