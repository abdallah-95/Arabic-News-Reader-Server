package Main;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dataset.Collector;
import net.sf.classifier4J.ClassifierException;
import nlp.TextClassifier;
import webscraping.TextExtractor;
import webscraping.URL;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;
	private JLabel label;
	public  static JLabel server_info;
	private JTextArea textArea;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
			
		/*
			TextClassifier tc=new TextClassifier();
		try {
			tc.train_stan();
			//Collector.txt_To_ts_file("Classification dataset/economy/economy.txt","economy");
			//tc.train_c4j();
	//	System.out.print(tc.classify_cfj_stan(""));
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
			Collector.txt_To_ts_file("Classification dataset/egypt/egypt.txt","egypt");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
*/
/*
		try {
			Collector.collect(URL.MASRAWY_SPORT_LOCAL,"sport");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame("News Reader Server");
		frame.setBounds(100, 100, 600, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
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
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TextExtractor te =new TextExtractor();
				

				try {
					
					
		        	   frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		        	   
		        	    
			        	 Connection  con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=News_Reader_Project;integratedSecurity=true");
			        	 
			        	 PreparedStatement ps=con.prepareStatement("Delete From News;");
			        	 ps.executeUpdate();
		        	   
					//ArrayList<TextExtractor> masrawySport=te.extract(URL.MASRAWY_SPORT_LOCAL, false, 0);
					//ArrayList<TextExtractor> masrawyEconomy=te.extract(URL.MASRAWY_Economy_LOCAL, false, 0);
					//ArrayList<TextExtractor> masrawyEgypt=te.extract(URL.MASRAWY_Egypt_LOCAL, false, 0);
					   //ArrayList<TextExtractor> CNNEgypt=te.extract(URL.CNN_Egypt, false, 0);
		        	   ArrayList<TextExtractor> elmasryelyoumEgypt = te.extract(URL.ELMasryELYoum_Egypt, false, 0);
		        	   ArrayList<TextExtractor> elmasryelyoumEconomy = te.extract(URL.ELMasryELYoum_Economy, false, 0);
		        	   ArrayList<TextExtractor> elmasryelyoumSport = te.extract(URL.ELMasryELYoum_Sport, false, 0);

					TextClassifier tc =new TextClassifier();
					/*
						for(int i=0;i<masrawySport.size();i++){
						PreparedStatement pstmt=con.prepareStatement("Insert into News (Headline,Story,News_agency,Category) Values(?,?,?,?);");
						pstmt.setString(1, masrawySport.get(i).headline);
						pstmt.setString(2, masrawySport.get(i).story);
						pstmt.setString(3, "مصراوى");
						pstmt.setString(4, "رياضة");

					    pstmt.execute();
						
						}
						
						
												 
						for(int i=0;i<masrawyEconomy.size();i++){
						PreparedStatement pstmt=con.prepareStatement("Insert into News (Headline,Story,News_agency,Category) Values(?,?,?,?);");
						pstmt.setString(1, masrawyEconomy.get(i).headline);
						pstmt.setString(2, masrawyEconomy.get(i).story);
						pstmt.setString(3, "مصراوى");
						pstmt.setString(4, "اقتصاد");

					    pstmt.execute();
						
						}
						
						
						
						for(int i=0;i<masrawyEgypt.size();i++){
							PreparedStatement pstmt=con.prepareStatement("Insert into News (Headline,Story,News_agency,Category) Values(?,?,?,?);");
							pstmt.setString(1, masrawyEgypt.get(i).headline);
							pstmt.setString(2, masrawyEgypt.get(i).story);
							pstmt.setString(3, "مصراوى");
							pstmt.setString(4, "اخبار مصر");

						    pstmt.execute();
							
							}*/
						
						for(int i=0;i<elmasryelyoumEgypt.size();i++){
							PreparedStatement pstmt=con.prepareStatement("Insert into News (Headline,Story,News_agency,Category) Values(?,?,?,?);");
							pstmt.setString(1, elmasryelyoumEgypt.get(i).headline);
							pstmt.setString(2, elmasryelyoumEgypt.get(i).story);
							pstmt.setString(3, "المصرى اليوم");
							pstmt.setString(4, "اخبار مصر");

						    pstmt.execute();
							
							}
						
						for(int i=0;i<elmasryelyoumEconomy.size();i++){
							PreparedStatement pstmt=con.prepareStatement("Insert into News (Headline,Story,News_agency,Category) Values(?,?,?,?);");
							pstmt.setString(1, elmasryelyoumEconomy.get(i).headline);
							pstmt.setString(2, elmasryelyoumEconomy.get(i).story);
							pstmt.setString(3, "المصرى اليوم");
							pstmt.setString(4, "اقتصاد");

						    pstmt.execute();
							
							}
						
						for(int i=0;i<elmasryelyoumSport.size();i++){
							PreparedStatement pstmt=con.prepareStatement("Insert into News (Headline,Story,News_agency,Category) Values(?,?,?,?);");
							pstmt.setString(1, elmasryelyoumSport.get(i).headline);
							pstmt.setString(2, elmasryelyoumSport.get(i).story);
							pstmt.setString(3, "المصرى اليوم");
							pstmt.setString(4, "رياضه");

						    pstmt.execute();
							
							}
						
//						for(int i=0;i<CNNEgypt.size();i++){
//							PreparedStatement pstmt=con.prepareStatement("Insert into News (Headline,Story,News_agency,Category) Values(?,?,?,?);");
//							pstmt.setString(1, CNNEgypt.get(i).headline);
//							pstmt.setString(2, CNNEgypt.get(i).story);
//							pstmt.setString(3, "سى ان ان عربي");
//							
//							String cat=tc.classify_cfj_stan(CNNEgypt.get(i).headline+" "+CNNEgypt.get(i).story);
//							pstmt.setString(4, cat);
//
//						    pstmt.execute();
//							
//							}
						
						
						String SQL = "Select * from News";  
					    Statement   stmt = con.createStatement();  
					    ResultSet rs = stmt.executeQuery(SQL);
					    String news="";
					    int headlines_num=0;
					    while(rs.next()){
					    	headlines_num++;
					    	news+=rs.getString(5)+"\t"+rs.getString(2)+"\n";
					    }
			        	   frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			        	   
						    
					    label.setText(String.valueOf(headlines_num));
					    textArea.setText(news);
        
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(60, 288, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnTestNlp = new JButton("Test NLP");
		btnTestNlp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nlp.Main nlp= new nlp.Main();
				try {
					nlp.nlp_main();
				} catch (ClassifierException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTestNlp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTestNlp.setBounds(428, 288, 89, 23);
		frame.getContentPane().add(btnTestNlp);
		
		JLabel lblDatabaseInfo = new JLabel("Database info:");
		lblDatabaseInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatabaseInfo.setBounds(10, 11, 89, 14);
		frame.getContentPane().add(lblDatabaseInfo);
		
		JLabel lblHeadLines = new JLabel("Headlines:");
		lblHeadLines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeadLines.setBounds(10, 30, 72, 14);
		frame.getContentPane().add(lblHeadLines);
		
	    label = new JLabel("");
		label.setBounds(74, 30, 46, 14);
		frame.getContentPane().add(label);
		
		JButton btnLaunchServer = new JButton("Launch Server");
		btnLaunchServer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLaunchServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				SwingWorker<Void, String> worker = new SwingWorker<Void,String>(){

					@Override
					protected Void doInBackground() throws Exception {

						publish("Waiting for a client to connect...");
						Server.launchServer();
						return null;
					}
					
					@Override
				     protected void process(List<String> chunks) {
						 for (final String string : chunks) {
							 Main.server_info.setText(string);
						    }
				     }
					
		    		   
		    	   };
		    	   
		    	   worker.execute();
				
				//Server.launchServer();
				//Server.recieve();
				
			}
		});
		btnLaunchServer.setBounds(231, 289, 115, 23);
		frame.getContentPane().add(btnLaunchServer);
		
		JLabel lblServerInfo = new JLabel("Server info:");
		lblServerInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServerInfo.setBounds(24, 340, 89, 14);
		frame.getContentPane().add(lblServerInfo);
		
	    server_info = new JLabel("");
	    server_info.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    server_info.setBounds(24, 365, 301, 23);
		frame.getContentPane().add(server_info);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 574, 222);
		frame.getContentPane().add(scrollPane);
		
	    textArea = new JTextArea();
	    textArea.setEditable(false);
	    textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
	}
	
}
