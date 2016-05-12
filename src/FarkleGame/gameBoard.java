package FarkleGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class gameBoard extends JFrame {
	private ArrayList<Player> players = new ArrayList<Player>();
	private JPanel contentPane;
	private JTextField textField;

	//called from main menu, creates the players, starts game
	public gameBoard(String nameOne, String nameTwo, boolean buyIn) {
		players.add(new Player(nameOne));
		players.add(new Player(nameTwo));
		createWindow();
		
	}
	
	private void createWindow() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		
		setTitle("Farkle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWhosTurn = new JLabel("whos turn");
		lblWhosTurn.setBounds(24, 11, 138, 14);
		contentPane.add(lblWhosTurn);
		
		JLabel lblCurrentTurn = new JLabel("current turn");
		lblCurrentTurn.setBounds(65, 11, 46, 14);
		contentPane.add(lblCurrentTurn);
		
		JLabel lblPlayerScore = new JLabel("Player 1 Score");
		lblPlayerScore.setBounds(292, 122, 76, 14);
		contentPane.add(lblPlayerScore);
		
		JLabel lblPlayerScore_1 = new JLabel("Player 2 score");
		lblPlayerScore_1.setBounds(292, 160, 71, 14);
		contentPane.add(lblPlayerScore_1);
		
		JLabel lblAvailableCombinations = new JLabel("Available Combinations");
		lblAvailableCombinations.setBounds(24, 122, 119, 14);
		contentPane.add(lblAvailableCombinations);
		
		JLabel lblCombinations = new JLabel("Combinations");
		lblCombinations.setBounds(158, 122, 76, 14);
		contentPane.add(lblCombinations);
		
		JLabel lblAvailablePointsIn = new JLabel("available points in roll");
		lblAvailablePointsIn.setBounds(24, 160, 119, 14);
		contentPane.add(lblAvailablePointsIn);
		
		JLabel lblRollpoints = new JLabel("rollPoints");
		lblRollpoints.setBounds(158, 160, 57, 14);
		contentPane.add(lblRollpoints);
		
		JLabel lblPleseSelectCombinations = new JLabel("Plese select combinations");
		lblPleseSelectCombinations.setBounds(10, 197, 138, 14);
		contentPane.add(lblPleseSelectCombinations);
		
		textField = new JTextField();
		textField.setBounds(139, 194, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFarkleCount = new JLabel("Farkle Count");
		lblFarkleCount.setBounds(292, 197, 71, 14);
		contentPane.add(lblFarkleCount);
		
		JLabel lblCount = new JLabel("Count");
		lblCount.setBounds(360, 197, 46, 14);
		contentPane.add(lblCount);
		
		JButton btnRoll = new JButton("Roll");0
		btnRoll.setBounds(84, 227, 89, 23);
		contentPane.add(btnRoll);
		
		JButton btnPass = new JButton("Pass");
		btnPass.setBounds(260, 227, 89, 23);
		contentPane.add(btnPass);
		
		validate();
	}
}
