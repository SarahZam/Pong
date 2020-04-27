import javax.swing.JFrame;

public class Main {

	// Main method
	public static void main(String[] args) {
		JFrame frame = new JFrame("Game"); //Making instance of JFrame

		Lob2 lob = new Lob2(); //making instance of Lob2 class

		frame.add(lob);
		frame.setSize(1200, 745);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting the size for the canvas frame
		frame.setResizable(false);
		frame.setVisible(true);

	}

}
