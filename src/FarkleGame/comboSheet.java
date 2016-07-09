package FarkleGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class comboSheet {

	private JFrame frame;

	public comboSheet() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("possible Combinations");
		frame.setBounds(100, 100, 194, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("1 = 100");
		label.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("5 = 50");
		label_1.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("111 = 300");
		label_2.setBounds(10, 61, 50, 14);
		frame.getContentPane().add(label_2);
		
		JLabel lblAnyNumber = new JLabel("4 any number = 1000");
		lblAnyNumber.setBounds(10, 105, 104, 14);
		frame.getContentPane().add(lblAnyNumber);
		
		JLabel label_3 = new JLabel("222 = 200 333=300 444=400...");
		label_3.setBounds(10, 86, 158, 14);
		frame.getContentPane().add(label_3);
		
		JLabel lblAnyNumber_1 = new JLabel("5 any number = 2000");
		lblAnyNumber_1.setBounds(10, 130, 104, 14);
		frame.getContentPane().add(lblAnyNumber_1);
		
		JLabel lblAnyNumber_2 = new JLabel("6 any number = 3000");
		lblAnyNumber_2.setBounds(10, 155, 110, 14);
		frame.getContentPane().add(lblAnyNumber_2);
		
		JLabel lblStraight = new JLabel("1-6 straight = 1500");
		lblStraight.setBounds(10, 180, 94, 14);
		frame.getContentPane().add(lblStraight);
		
		JLabel lblThreePair = new JLabel("Three Pair = 1500");
		lblThreePair.setBounds(10, 205, 87, 14);
		frame.getContentPane().add(lblThreePair);
		
		JLabel lblTwoTriplets = new JLabel("Two triplets = 2500");
		lblTwoTriplets.setBounds(10, 230, 94, 14);
		frame.getContentPane().add(lblTwoTriplets);
	}
}
