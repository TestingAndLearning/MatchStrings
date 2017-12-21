package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

/** This is the java main class. It takes care of setting up the frames and 
 * 	parsing information to the main bot that runs commands. **/
public class Frames extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	private JButton btnStart;
	private JLabel versionLabel;
	public JTextArea pinArea;
	public JTextArea pinArea2;
	public JTextArea pinArea3;
	private JScrollPane scroll;
	private JScrollPane scroll2;
	private JScrollPane scroll3;
	public JScrollBar vertical;
	private JButton btnExit;
	private JButton btnSort;
	private JButton btnStop;
	private JButton btnResume;
	private String vers = "V. 1.30";
	private JRadioButton opened, triaged;
	ButtonGroup buttonGroup = new ButtonGroup();
	private Finder bot2;
	
	


	/** Launch the application. **/
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Frames frame = new Frames();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the frame. **/
	public Frames()
	{
		setUndecorated(false);
		setTitle("Vlookup Alternative");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 425);	// .., .., length, width
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(30, 10, 430, 115);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNumberOfPins = new JLabel("Number of Pins");
		lblNumberOfPins.setBounds(21, 21, 94, 20);
		panel.add(lblNumberOfPins);
		lblNumberOfPins.setVisible(false);

		opened = new JRadioButton("Opened");
		triaged = new JRadioButton("Triaged");
		buttonGroup.add(triaged);
		buttonGroup.add(opened);
		triaged.setActionCommand("Triaged");
		opened.setActionCommand("Opened");
		opened.setBounds(21, 40, 80, 30);
		triaged.setBounds(100, 40, 80, 30);
		//panel.add(triaged);
		//panel.add(opened);
		opened.setSelected(true);
		
		JLabel firstColumn = new JLabel("First Column");
		firstColumn.setBounds(30, 140, 200, 20);
		contentPane.add(firstColumn);
		
		pinArea = new JTextArea ("");
		scroll = new JScrollPane (pinArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		scroll.setBounds(30, 160, 200, 200);
		
		JLabel secondColumn = new JLabel("Second Column");
		secondColumn.setBounds(260, 140, 150, 20);
		contentPane.add(secondColumn);
		
		JLabel resultColumn = new JLabel("Result Column");
		resultColumn.setBounds(490, 140, 150, 20);
		contentPane.add(resultColumn);
		
		pinArea2 = new JTextArea ("");
		scroll2 = new JScrollPane (pinArea2);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll2);
		scroll2.setBounds(260, 160, 200, 200);
		
		pinArea3 = new JTextArea ("");
		pinArea3.setEditable(false);
		scroll3 = new JScrollPane (pinArea3);
		scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll3);
		scroll3.setBounds(490, 160, 200, 200);
		
		vertical = scroll3.getVerticalScrollBar();
		
		btnSort = new JButton("Sort");
		btnSort.addActionListener(this);
		btnSort.setActionCommand("Sort");
		btnSort.setBounds(200, 16, 89, 23);
		panel.add(btnSort);

		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnStart.setActionCommand("Start");
		btnStart.setBounds(315, 16, 89, 23);
		panel.add(btnStart);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		btnExit.setActionCommand("Exit");
		btnExit.setBounds(315, 73, 89, 23);
		panel.add(btnExit);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(this);
		btnStop.setActionCommand("Stop");
		btnStop.setBounds(315, 73, 89, 23);
		panel.add(btnStop);
		btnStop.setVisible(false);
		
		btnResume = new JButton("Resume");
		btnResume.addActionListener(this);
		btnResume.setActionCommand("Resume");
		btnResume.setBounds(315, 73, 89, 23);
		panel.add(btnResume);
		btnResume.setVisible(false);
		
		progressBar = new JProgressBar();
		progressBar.setString("Please wait...");
		progressBar.setStringPainted(true);
		progressBar.setBounds(315, 43, 90, 19);
		setProgressState(true);
		panel.add(progressBar);

		versionLabel = new JLabel("Auto Release " + vers);
		versionLabel.setForeground(Color.DARK_GRAY);
		versionLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		versionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		versionLabel.setBounds(373, 300, 89, 14);
		contentPane.add(versionLabel);
	}

	void setProgressState(final boolean done)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				progressBar.setVisible(!done);
				progressBar.setIndeterminate(!done);

				btnStart.setEnabled(done);
				
				btnExit.setVisible(done);
				btnStop.setVisible(!done);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getActionCommand().equals("Start"))
		{
			try
			{				
				Finder bot = new Finder(Frames.this, buttonGroup.getSelection().getActionCommand(), 
						true, pinArea.getText(), pinArea2.getText());

				//JOptionPane.showMessageDialog(null, "Before continuing: \n 1. Make sure ICS is on the left screen. \n 2. Make sure CDS is on the right screen.", "Warning",
						//JOptionPane.WARNING_MESSAGE);
				ExecutorService executor = Executors.newCachedThreadPool();
				executor.execute(bot);
				
				bot2 = bot;
				
			} catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Please enter a number. ", "Error", JOptionPane.ERROR_MESSAGE);
			}
			

		}
		
		if (evt.getActionCommand().equals("Sort"))
		{
			String pinAreaTemp[] = pinArea.getText().split("\n");
			int[] pinAreaInt = new int[pinAreaTemp.length];
			for(int i = 0; i < pinArea.getText().split("\n").length; i++) 
			{
			    pinAreaInt[i] = Integer.parseInt(pinAreaTemp[i]);
			}
			
			
			
			Arrays.sort(pinAreaInt);
			String currentPinText = Arrays.toString(pinAreaInt).replace(",", "\r\n").replace("[", "").replace("]", "").replace(" ", "");
			pinArea.setText(currentPinText);
			
		} 
		
		if (evt.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		} 
		
		if (evt.getActionCommand().equals("Stop"))
		{
			System.out.println("Stop button pressed. ");
			bot2.stop();
			btnStop.setVisible(false);
			btnResume.setVisible(true);
		} 		
		
		if (evt.getActionCommand().equals("Resume"))
		{
			System.out.println("Resume button pressed. ");
			bot2.resume();
			btnResume.setVisible(false);
			btnStop.setVisible(true);
		} 	
		
		else
		{
			System.out.println("Clicked button!");
		}
	}

	public String choice()
	{
		return buttonGroup.getSelection().getActionCommand();
	}
	
}