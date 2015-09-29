import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DrawImage extends JPanel{
	JFrame frame;
	int[][] image;
	//JPanel panel;
	
	public DrawImage(int[][] image){
		this.image = image;
		frame = new JFrame();
		//panel = new JPanel();
		
		
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500 , 500);
		frame.setVisible(true);
		
	}
	

	public void paintComponent(Graphics g){
		for(int i=0; i<image.length; i++){
			for(int j=0; j<image[i].length; j++){
				if(image[i][j] > 127){
					g.drawLine(j, i, j, i);
				}
			}
		}
	}
	
}
