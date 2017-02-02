import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Steganography_View extends JFrame{
	private static int WIDTH  = 800;
	private static int HEIGHT = 600;
	
	private JScrollBar scrollImage;
	private JScrollBar scrollText;
	private JButton encodeButton;
	private JButton decodeButton;
	private JButton textButton;
	private JButton stegoButton;
	private JButton unstegoButton;
	private JLabel image_input;
	private JLabel original_image;
	private JLabel chosen_text;
	private JMenu file;
	private JMenuItem encode;
	private JMenuItem decode;
	private JMenuItem exit;
	public Steganography_View(String name){
		super(name);
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		encode = new JMenuItem("Stego");
		file.add(encode);
		decode = new JMenuItem("Unstego");
		file.add(decode);
		file.addSeparator();
		exit = new JMenuItem("Exit");
		file.add(exit);
		
		menu.add(file);
		setJMenuBar(menu);
		
		setLayout(new BorderLayout());
		setBounds(0, 0, WIDTH, HEIGHT);
		setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setVisible(true);
	}
	public JMenuItem getEncode(){
		return encode;			
	}
	public JMenuItem getDecode(){
		return decode;
	}
	public JMenuItem getExit(){
		return exit;				
	}
	public JLabel getImageInput(){ 
		return image_input;		
	}
	public JLabel getOriginalImage(){
		return original_image;
	}
	public JLabel getChosenText(){
		return chosen_text;
	}
	public JPanel getStegoPanel() { 
		return new Stego_Panel();	
	}
	public JPanel getImagePanel(){ 
		return new Unstego_Panel();	
	}
	public JButton getTButton(){ 
		return textButton;		
	}
	public JButton getEButton(){ 
		return encodeButton;		
	}
	public JButton getSButton(){
		return stegoButton;
	}
	public JButton getUButton(){
		return unstegoButton;
	}
	public JButton getDButton(){ 
		return decodeButton;		
	}
	private class Stego_Panel extends JPanel{
		public Stego_Panel(){
			setLayout(null);
			
			JLabel Matrix_Gif;
			Icon mygif;
			mygif = new ImageIcon(getClass().getResource("Matrix.gif"));
			Matrix_Gif = new JLabel(mygif);
			Matrix_Gif.setBounds(0, 0, 800, 600);
	    	
	    	textButton = new JButton("Choose Your Text File");
	    	textButton.setBounds(10, 460, 200, 50);
	    	
	    	encodeButton = new JButton("Choose Your Image File");
	    	encodeButton.setBounds(250, 460, 200, 50);
	    	
	    	stegoButton = new JButton("Stego Now");
	    	stegoButton.setBounds(560, 460, 200, 50);
	    	
	    	chosen_text = new JLabel();
	    	JScrollPane scrollText = new JScrollPane(chosen_text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			scrollText.setBounds(10, 10, 750, 50);
			chosen_text.setHorizontalAlignment(JLabel.CENTER);
	    	
	    	original_image = new JLabel();
			JScrollPane scrollImage = new JScrollPane(original_image,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			scrollImage.setBounds(10, 100, 750, 350);
			original_image.setHorizontalAlignment(JLabel.CENTER);
	   
	    	add(textButton);
	    	add(encodeButton);
	    	add(stegoButton);
	    	add(scrollText);
	    	add(scrollImage);
	    	add(Matrix_Gif);
		}
	}
	private class Unstego_Panel extends JPanel{
		public Unstego_Panel(){
			setLayout(null);
			
			JLabel Matrix_Gif;
			Icon mygif;
			mygif = new ImageIcon(getClass().getResource("Matrix.gif"));
			Matrix_Gif = new JLabel(mygif);
			Matrix_Gif.setBounds(0, 0, 800, 600);
			
			unstegoButton = new JButton("Choose Image");
			unstegoButton.setBounds(10, 450, 200, 50);
			decodeButton = new JButton("Unstego Now");
			decodeButton.setBounds(560, 450, 200, 50);
			
			image_input = new JLabel();
			JScrollPane scrollImage = new JScrollPane(image_input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			scrollImage.setBounds(10, 10, 760, 400);
			image_input.setHorizontalAlignment(JLabel.CENTER);
	    	
	    	add(unstegoButton);
	    	add(decodeButton);
	    	add(scrollImage);
	    	add(Matrix_Gif);
	    }
	 }
	public static void main(String args[]){
		new Steganography_View("Steganography v1.0");
	}
}
