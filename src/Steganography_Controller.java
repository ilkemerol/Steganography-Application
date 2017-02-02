import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Steganography_Controller{
	private Steganography_View view;
	private Steganography model;
	
	private JPanel decode_panel;
	private JPanel encode_panel;
	private JButton	encodeButton;
	private JButton decodeButton;
	private JButton textButton;
	private JButton stegoButton;
	private JButton unstegoButton;
	private JLabel image_input;
	private JLabel original_image;
	private JLabel chosen_text;
	private JMenuItem encode;
	private JMenuItem decode;
	private JMenuItem exit;
	private Encode enc;
	private Decode dec;
	private TextButton txtButton;
	private EncodeButton encButton;
	private StegoButton stgButton;
	private DecodeButton decButton;
	private UnstegoButton ustgButton;
	private String stat_path = "";
	private String stat_name = "";
	private String text = null;
	private String ext = null;
	private String name = null;
	private String path = null;
	private String image = null;
	private int valid = 0;
	
	public Steganography_Controller(Steganography_View aView, Steganography aModel){
		view  = aView;
		model = aModel;
		encode_panel = view.getStegoPanel();
		decode_panel = view.getImagePanel();
		image_input	= view.getImageInput();
		original_image = view.getOriginalImage();
		chosen_text = view.getChosenText();
		textButton = view.getTButton();
		encodeButton = view.getEButton();
		stegoButton = view.getSButton();
		decodeButton = view.getDButton();
		unstegoButton = view.getUButton();
		encode = view.getEncode();
		decode = view.getDecode();
		exit = view.getExit();
		enc = new Encode();
		encode.addActionListener(enc);
		dec = new Decode();
		decode.addActionListener(dec);
		exit.addActionListener(new Exit());
		txtButton = new TextButton();
		textButton.addActionListener(txtButton);
		encButton = new EncodeButton();
		encodeButton.addActionListener(encButton);
		stgButton = new StegoButton();
		stegoButton.addActionListener(stgButton);
		decButton = new DecodeButton();
		decodeButton.addActionListener(decButton);
		ustgButton = new UnstegoButton();
		unstegoButton.addActionListener(ustgButton);
		encode_view();
	}
	private void encode_view(){
		chosen_text.setText("");
		original_image.setIcon(null);
		view.setContentPane(encode_panel);
		view.setVisible(true);
	}
	private void decode_view(){
		image_input.setIcon(null);
		view.setContentPane(decode_panel);
		view.setVisible(true);
	}
	private class Encode implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JOptionPane.showMessageDialog(view, "Your Frame is changing!", "Success!", JOptionPane.INFORMATION_MESSAGE);
			encode_view();
		}
	}
	private class Decode implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JOptionPane.showMessageDialog(view, "Your Frame is changing!", "Success!", JOptionPane.INFORMATION_MESSAGE);
			decode_view();
		}
	}
	private class Exit implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.exit(0);
		}
	}
	//Choose text file.
	private class TextButton implements ActionListener{
		public void actionPerformed(ActionEvent event){
			File directory = null;
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new ChooserFilter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				directory = chooser.getSelectedFile();
			}
			BufferedReader reader = null;
			try{
				ext = ChooserFilter.getExtension(directory);
				if(ext.equals("txt")){
					reader = new BufferedReader(new FileReader(directory));
					try{
						text = reader.readLine();
						if(text.length() > 80){
							JOptionPane.showMessageDialog(view, "The File include more than 80 char!", "Error!", JOptionPane.INFORMATION_MESSAGE);
							chosen_text.setText("ERROR OCCURS! PLEASE SELECT FILE AGAIN");
						}
						else{
							valid++;
							chosen_text.setText(text);
						}
					}
					catch (IOException except){
						except.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(view, "The File is not a TXT file!", "Error!", JOptionPane.INFORMATION_MESSAGE);
					chosen_text.setText("ERROR OCCURS! PLEASE SELECT TEXT FILE AGAIN");
				}
			}
			catch(FileNotFoundException except){
				except.printStackTrace();
			}
		}
	}
	//Choose image file
	private class EncodeButton implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new ChooserFilter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					ext  = ChooserFilter.getExtension(directory);
					if(ext.equals("bmp")){
						image = directory.getPath();
						name = directory.getName();
						path = directory.getPath();
						path = path.substring(0,path.length()-name.length()-1);
						name = name.substring(0, name.length()-4);
						original_image.setIcon(new ImageIcon(ImageIO.read(new File(image))));
						valid++;
					}
					else{
						JOptionPane.showMessageDialog(view, "The File is not a BMP file!", "Error!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception except) {
				JOptionPane.showMessageDialog(view, "The File cannot be opened!", "Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	//Encryption start.
	private class StegoButton implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(valid == 2){
				String stegan = JOptionPane.showInputDialog(view,"Enter output file name:", "File name",JOptionPane.PLAIN_MESSAGE);
				if(model.encode(path,name,ext,stegan,text)){
				JOptionPane.showMessageDialog(view, "The Image was encoded Successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
				decode_view();
				}
				else{
				JOptionPane.showMessageDialog(view, "The Image could not be encoded!", "Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(view, "Missing File or Files!", "Error!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	//Choose encrypt image.
	private class UnstegoButton implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new ChooserFilter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					ext = ChooserFilter.getExtension(directory);
					if(ext.equals("bmp")){
						image = directory.getPath();
						stat_name = directory.getName();
						stat_path = directory.getPath();
						stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
						stat_name = stat_name.substring(0, stat_name.length()-4);
						image_input.setIcon(new ImageIcon(ImageIO.read(new File(image))));
					}
					else{
						JOptionPane.showMessageDialog(view, "The File is not a BMP file!", "Error!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception except) {
				JOptionPane.showMessageDialog(view, "The File cannot be opened!", "Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	//Decryption start.
	private class DecodeButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String message = model.decode(stat_path, stat_name);
			if(message != ""){
				encode_view();
				JOptionPane.showMessageDialog(view, "The Image was decoded Successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(view, "The Image was decoded Successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
				File myfile = new File("SecretMessage.txt");
				if(!myfile.exists()){
					try{
						myfile.createNewFile();
					}
					catch (IOException event){
						event.printStackTrace();
					}
				}
				FileWriter fileWriter = null;
				try{
					fileWriter = new FileWriter(myfile, false);
				}
				catch (IOException event) {
					event.printStackTrace();
				}
				BufferedWriter bWriter = new BufferedWriter(fileWriter);
				try{
					bWriter.write(message);
				}
				catch (IOException event){
					event.printStackTrace();
				}
				try{
					bWriter.close();
				}
				catch (IOException event){
					event.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(view, "The Image could not be decoded!", "Error!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	public static void main(String args[]){
		new Steganography_Controller(new Steganography_View("Steganography v1.7"),new Steganography());
	}
}