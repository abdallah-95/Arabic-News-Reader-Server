package nlp;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dataset.Collector;
import edu.stanford.nlp.ling.CoreLabel;
import net.sf.classifier4J.ClassifierException;
import webscraping.TextExtractor;
import webscraping.URL;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JPanel;

public class Main {

	public static JFrame frame;

	/**
	 * Launch the application.
	 * @throws ClassifierException
	 */
	public static void nlp_main() throws ClassifierException {


	/*	TextClassifier tc=new TextClassifier();
		try {
			//tc.train_stan();
			tc.train_c4j();
		System.out.print(tc.classify_cfj_stan(""));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
/*
		try {
			Collector.txt_To_ts_file("Classification dataset/sport/sport.txt","sport");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/

/*
		try {
			Collector.collect(URL.MASRAWY_SPORT_LOCAL,"sport");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.initialize();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		//Statement stmt = null;
		//ResultSet rs = null;  
	//	try {
			//Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=News_Reader_Project","Abdallah","0000");
			// String SQL = "Select * from News";  
	      //   stmt = con.createStatement();  
	        // rs = stmt.executeQuery(SQL); 
			
			//PreparedStatement mystmt2=con.prepareStatement("Select * From News Where ID = ?");
			//mystmt2.setString(1, "2");
			//rs = mystmt2.executeQuery();
	      //   while (rs.next()) {  
	         //    System.out.println(rs.getString(1) + " " + rs.getString(2));  
	       //   }  
		
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}

		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame("Arabic News Classification Test");
		frame.setBounds(100, 100, 600, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);


		    try {

		    	UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
		    }
		    catch (UnsupportedLookAndFeelException e) {
		       // handle exception
		    }
		    catch (ClassNotFoundException e) {
		       // handle exception
		    }
		    catch (InstantiationException e) {
		       // handle exception
		    }
		    catch (IllegalAccessException e) {
		       // handle exception
		    }
		frame.getContentPane().setLayout(null);



		JLabel lblInputText = new JLabel("Input:");
		lblInputText.setBounds(10, 11, 84, 32);
		lblInputText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblInputText);


		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setBounds(295, 11, 63, 32);
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblOutput);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(295, 40, 289, 265);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 40, 275, 265);
		frame.getContentPane().add(scrollPane_1);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrollPane_1.setViewportView(textArea_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 316, 225, 23);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Tokenize", "Normalize", "Remove stop words", "Stem", "Part of speech tag/trained_tagger", "Part of speech tag/stanford_tagger", "Classify"}));
		frame.getContentPane().add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 347, 275, 23);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Extract local sport news-Masrawy"}));
		frame.getContentPane().add(comboBox_1);

		POSTagger tagger=new POSTagger("trained_tagger");
		POSTagger tagger2=new POSTagger("arabic_tagger");
		RootStemmer rs=new RootStemmer();

		Tokenizer tok=new Tokenizer();
		TextExtractor te=new TextExtractor();

		JButton btnProcess = new JButton("Process");
		btnProcess.setBounds(484, 316, 100, 23);
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (comboBox.getSelectedItem().toString()){

				        case"Tokenize":
				        	textArea.setText("");
				        	String[] tokens=tok.tokenize(textArea_1.getText());
				        	for(int i=0;i<tokens.length;i++){
				        	    textArea.append(tokens[i]);
				        	    textArea.append("\n");
				        	}
				        	break;

				        case"Part of speech tag/trained_tagger":
				        	textArea.setText("");
				        	String tt=tagger.tag_text(textArea_1.getText());
				        	textArea.append(tt);
				        	break;

				        case"Part of speech tag/stanford_tagger":
				        	textArea.setText("");
				        	String tt2=tagger2.tag_text(textArea_1.getText());
				        	textArea.append(tt2);
				        	break;

				        case "Normalize":
				        	textArea.setText("");
				        	String norm_txt=Normalizer.normalize(textArea_1.getText());
				        	textArea.append(norm_txt);
				        	break;

				        case "Stem":
				        	textArea.setText("");
                            String stemmedtxt=rs.stem(textArea_1.getText());
                            textArea.setText(stemmedtxt);
				        	break;

				        case "Classify":
				        	textArea.setText("");
				        	TextClassifier tc=new TextClassifier();
					try {
						tc.train_c4j();
						String cat =tc.classify_cfj_stan(textArea_1.getText());
						textArea.setText(cat);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (ClassifierException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
					
				        case "Remove stop words":
				        	textArea.setText("");
					try {
						String res[]=TextPreparator.remove_stopwords_char(textArea_1.getText());
						for(int i=0;i<res.length;i++){
							textArea.append(res[i]+" ");
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				       break; 	
				        
						

                 }

				switch (comboBox_1.getSelectedItem().toString()){

				           case "Extract local sport news-Masrawy":

				        	   frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	textArea.setText("");

				        	ArrayList<TextExtractor> result = null;


					try {
						result = te.extract(URL.CNN_Egypt,false,0);
					} catch (Exception e1) {

						e1.printStackTrace();
					}


					 for(int i=0;i<result.size();i++){
					    	textArea.append(result.get(i).headline +
					    			"                                        "+
					    			         result.get(i).story +"     "+
					    			         ""
					    			         + "***************************");
					    }
					    frame.setCursor(Cursor.getDefaultCursor());
					    break;

				}


			}
		});
		btnProcess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(btnProcess);






	}
}
