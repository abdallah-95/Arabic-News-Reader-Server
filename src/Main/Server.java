package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import nlp.Tokenizer;

public class Server {
	
	

	private static ServerSocket serverSocket = null;
	private static Socket socket = null; 
	
	public static void launchServer(){

	
    try {
		
    	serverSocket =new ServerSocket(60123);
		
    	//System.out.println("Waiting for a client to connect...");
    	
    	
    	
    	SwingWorker<Void, String> worker = new SwingWorker<Void,String>(){


			@Override
			protected Void doInBackground() throws Exception {

				System.out.println("sw");
				socket=serverSocket.accept();
				publish("A client has connected.");
				System.out.println("sa");
		    	recieve();
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
			

    	
    //	Main.server_info.setText("Waiting for a client to connect...");
    	// socket=serverSocket.accept();
    	//System.out.println("A client has connected.");
    //	Main.server_info.setText("A client has connected.");
			
    	
    	
	} catch (IOException e) {
		
		e.printStackTrace();
	}
}
	public static void recieve(){
		
		
        	BufferedReader br;
        	Tokenizer tk=new Tokenizer();
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg;
				String news_agency="";
				String cat="";
				msg=br.readLine();
					//System.out.println("Client has sent:"+" "+msg);
				//Main.server_info.setText("Client has sent:"+" "+msg);
					
					String [] tokens=tk.tokenize(msg);
					if(tokens[0].equals("المصري") || tokens[0].equals("المصرى")){
						news_agency+="المصرى"+" "+tokens[1];
					if(tokens.length==4)
						cat+=tokens[2]+" "+tokens[3];
					else
						cat=tokens[2];
					}
					
					if(tokens[0].equals("مصراوي")){
						news_agency+=tokens[0];
					if(tokens.length==3)
						cat+=tokens[1]+" "+tokens[2];
					else
						cat=tokens[1];
					}
				send(cat,news_agency);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	

        	
	}
	public static void send(String cat,String news_agency) throws SQLException{
 	   Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=News_Reader_Project;integratedSecurity=true");

 	  PreparedStatement pstmt=con.prepareStatement("Select Headline,Story from News_Reader_Project.dbo.News  Where News_agency=? AND Category=?");
		pstmt.setNString(1, news_agency);
		pstmt.setNString(2, cat);

		ResultSet rs =pstmt.executeQuery();
		int x=0;
		while(rs.next()){
			
	   	    	x++;
	    }
		String [] news = new String[(x*2)];
		
	    pstmt=con.prepareStatement("Select Headline,Story from News_Reader_Project.dbo.News  Where News_agency=? AND Category=?");
		pstmt.setNString(1,news_agency);
		pstmt.setNString(2, cat);
		rs =pstmt.executeQuery();

		int y=0;
		
		while(rs.next()){
			
			
	    	news[y]=rs.getString("Headline");
	    
	    	y++;
	    	news[y]=rs.getString("Story");
	    	
	    	y++;
	    }
		
		try {

			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(news);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
}
