import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

class ThreadClass extends JFrame implements ActionListener, Runnable {
	
	private JButton btn = new JButton("Stop Copy");
	BufferedReader br; 
	PrintWriter pw;
	File file = new File("C:\\Users\\SB\\Desktop\\test_java.txt"); 
	File outfile = new File("C:\\Users\\SB\\Desktop\\test_java_out.txt");
	int progress = 0;
	JProgressBar jpb = new JProgressBar();
	JLabel success = new JLabel("SUCCESS!");
	
	ThreadClass(){
		Container cont = getContentPane();
		cont.setLayout(new FlowLayout());
		cont.add(jpb);
		cont.add(btn);
		cont.add(success);
		success.setVisible(false);
		jpb.setStringPainted(true);
		jpb.setValue(progress);
		btn.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try{br.close();}
		catch(Exception er) {
			er.printStackTrace();
		}
		pw.close();
	}
	
	public void run() {
		String st;
	    try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
	    try {
			pw = new PrintWriter(outfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	    int inlength = (int)file.length();
	    int outlength = 0;
	    try {
			while ((st = br.readLine()) != null) {	
			  pw.append(st);
			  outlength += (int)st.length();
			  progress = (int)(outlength*100/inlength);
			  jpb.setValue(progress);
			  System.out.println(progress);
			  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			if(st==null) {
				jpb.setValue(100);
				jpb.setVisible(false);
				btn.setVisible(false);
				success.setVisible(true);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    pw.close();
	}
}

public class SwingTesting{
	public static void main(String[] args) {
		ThreadClass pb = new ThreadClass();
		pb.setSize(200,200);
		pb.setVisible(true);
		pb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pb.run();
	}
}