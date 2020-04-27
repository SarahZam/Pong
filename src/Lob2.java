import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Lob2 extends JComponent implements ActionListener, KeyListener {

	protected Sound sound; // making instance of Sound class
	// For the paddle and ball
	protected int paddleX = 1;
	protected double ballX = 100;
	protected double ballY = 100;

	// Starbucks Lives
	Image image = new ImageIcon("starbucksLives.png").getImage();
	Image background = new ImageIcon("BackgroundPic.png").getImage();

	// for the falling letters

	// x coordinates for falling letter
	protected int LetX1 = 100;
	protected int LetX2 = 500;
	protected int LetX3 = 400;
	protected int LetX4 = 600;
	protected int LetX5 = 850;
	protected int LetX6 = 750;
	protected int LetX7 = 200;
	protected int LetX8 = 900;
	protected int LetX9 = 650;
	protected int LetX10 = 1050;

	// y coordinates for falling letters
	protected int Lety1 = 0;
	protected int Lety2 = 0;
	protected int Lety3 = 0;
	protected int Lety4 = 0;
	protected int Lety5 = 0;
	protected int Lety6 = 0;
	protected int Lety7 = 0;
	protected int Lety8 = 0;
	protected int Lety9 = 0;
	protected int Lety10 = 0;

	// For the falling extra life
	protected double ExLifeX = 1000;
	protected double ExLifeY = -50;

	protected double ExLifeX2 = 500;
	protected double ExLifeY2 = -50;

	// Random variables to get letters to show up on screen
	protected int x1 = 0;
	protected int y = 300;
	protected int start = 0;
	protected int l1 = 500;
	protected int l2 = 3000;
	protected int l3 = 3000;
	protected int s1 = 400;
	protected int s2 = 4000;

	// Variables for the game
	protected int lives = 3;
	protected int score = 0;
	protected int count = 3;

	// Timers
	protected Timer timer1 = new Timer(5, this);
	protected Timer timer2;
	protected Timer timer3;
	protected Timer timer4;

	// For calculation of projectile
	protected double startX, startY;
	private double xSpeed, ySpeed;
	double angle = Math.toRadians(45);
	double speed = 20;
	private double time, deltaTime = 0.1;
	protected double bSpeed = -speed * Math.sin(angle);
	protected double G = 9.8;
	protected double aVar = 0;
	protected double j = 1;
	protected double f = 1;
	protected double d = 0;

	// For ball Color
	int bR = 145;
	int bG = 23;
	int bB = 200;

	// For routined falling of the ball
	protected int Count2;

	// Constructor
	public Lob2() {
		sound = new Sound();

		timer2 = new Timer(10, new TimerCallBack()); //ball movements and falling letters movements
		timer3 = new Timer(1000, new CountDown());
		timer4 = new Timer(1000, new Start());
		timer4.start();

		//starting points for the ball
		startX = 100; 
		startY = 100;

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		// For the paddle
		g.setColor(Color.YELLOW);
		g.fillRect(paddleX, 700, 100, 10);
		// TimeBar
		g.setColor(Color.YELLOW);
		g.fillRect(5, 5, y, 50);
		// Ball
		g.setColor(new Color(bR, bG, bB));
		g.fillOval((int) ballX, (int) ballY, 50, 50);
		// Extra Life
		g.drawImage(image, (int) ExLifeX, (int) ExLifeY, 50, 50, null);
		g.drawImage(image, (int) ExLifeX2, (int) ExLifeY2, 50, 50, null);

		if (lives == 5) {
			
			//Starbucks drink images
			g.drawImage(image, 880, 20, 50, 50, null);
			g.drawImage(image, 940, 20, 50, 50, null);
			g.drawImage(image, 1000, 20, 50, 50, null);
			g.drawImage(image, 1060, 20, 50, 50, null);
			g.drawImage(image, 1120, 20, 50, 50, null);

		}

		if (lives == 4) {

			g.drawImage(image, 940, 20, 50, 50, null);
			g.drawImage(image, 1000, 20, 50, 50, null);
			g.drawImage(image, 1060, 20, 50, 50, null);
			g.drawImage(image, 1120, 20, 50, 50, null);

		}

		if (lives == 3) {
			g.drawImage(image, 1000, 20, 50, 50, null);
			g.drawImage(image, 1060, 20, 50, 50, null);
			g.drawImage(image, 1120, 20, 50, 50, null);

		} else if (lives == 2) {

			g.drawImage(image, 1060, 20, 50, 50, null);
			g.drawImage(image, 1120, 20, 50, 50, null);
		}

		else if (lives == 1) {
			g.drawImage(image, 1120, 20, 50, 50, null);

		} else if (lives == 0) {
			l3 = 420;
			timer1.stop();
			timer2.stop();
			timer3.stop();
		}

		//word and letters showing up on the screen
		g.setColor(Color.BLUE);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.drawString("Score: " + (score), 310, 50);

		g.setColor(Color.BLUE);
		g.setFont(new Font("Helvetica", Font.BOLD, 50));
		g.drawString("Start Game in " + (count), s1, 420);

		g.setColor(Color.BLUE);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("Good Job! ", s2, 360);
		g.drawString("Total Score: " + (score), s2, 420);

		g.setColor(Color.BLUE);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 70));
		g.drawString("Game Over", l3, 350);

		// A
		g.setColor(Color.GREEN);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("A", LetX6, Lety6);

		// A
		g.setColor(Color.GREEN);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("A", LetX1, Lety1);

		// B
		g.setColor(Color.BLUE);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("B", LetX2, Lety2);

		// B
		g.setColor(Color.BLUE);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("B", LetX7, Lety7);

		// C
		g.setColor(Color.ORANGE);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("C", LetX3, Lety3);

		// C
		g.setColor(Color.ORANGE);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("C", LetX8, Lety8);

		// D
		g.setColor(Color.RED);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("D", LetX4, Lety4);

		// D
		g.setColor(Color.RED);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("D", LetX9, Lety9);

		// F
		g.setColor(Color.RED);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("F", LetX5, Lety5);

		// F
		g.setColor(Color.RED);
		g.setFont(new Font("AmercanTypewriter", Font.BOLD, 50));
		g.drawString("F", LetX10, Lety10);

		//losing lives
		if (ballY > 679) {
			sound.shockSound();
			lives--;
			ballY = 679;
		}

		// CatchingA
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX1, Lety1, 95, 10))) {
			sound.pointSound();
			score += 4;
			LetX1 = 5000;
		}
		// CatchingB
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX2, Lety2, 95, 10))) {
			sound.pointSound();
			score += 3;
			LetX2 = 5000;
		}

		// CatchingC
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX3, Lety3, 95, 10))) {
			sound.pointSound();
			score += 2;
			LetX3 = 5000;
		}

		// CatchingD
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX4, Lety4, 95, 10))) {
			sound.shockSound();
			score -= 1;
			LetX4 = 5000;
		}

		// CatchingF
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX5, Lety5, 95, 10))) {
			sound.shockSound();
			score -= 3;
			LetX5 = 5000;
		}

		// CatchingA
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX6, Lety6, 95, 10))) {
			sound.pointSound();
			score += 4;
			LetX6 = 5000;
		}
		// CatchingB
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX7, Lety7, 95, 10))) {
			sound.pointSound();
			score += 3;
			LetX7 = 5000;
		}

		// CatchingC
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX8, Lety8, 95, 10))) {

			score += 2;
			sound.pointSound();
			LetX8 = 5000;
		}

		// CatchingD
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX9, Lety9, 95, 10))) {
			sound.shockSound();
			score -= 1;
			LetX9 = 5000;
		}

		// CatchingF
		if (new Rectangle(paddleX, 700, 100, 10).intersects(new Rectangle(LetX10, Lety10, 95, 10))) {
			sound.shockSound();
			score -= 3;
			LetX10 = 5000;
		}

		// Extra Life
		if (new Rectangle((int) ExLifeX, (int) ExLifeY, 50, 50).intersects(new Rectangle(paddleX, 700, 100, 10))) {
			sound.pointSound();
			ExLifeX = 50000;
			lives++;
		}
		if (new Rectangle((int) ExLifeX2, (int) ExLifeY2, 50, 50).intersects(new Rectangle(paddleX, 700, 100, 10))) {
			sound.pointSound();
			ExLifeX2 = 50000;
			lives++;
		}
	}

	//starting game and countdown
	public class Start implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (count > 0) {
				timer1.stop();
				timer2.stop();
				timer3.stop();
				// timer4.start();

				count--;
			}
			else {
				timer1.start();
				timer2.start();
				timer3.start();
				timer4.stop();
				
				l1 = 2000;
				s1 = 2000;
			}
			repaint();
		}

	}

	//countdown for timebar
	protected class CountDown implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (y > 0) {
				y -= 5;
			} 
			else  {
				l2 = 350;
				s2 = 400;
				timer1.stop();
				timer2.stop();
				timer3.stop();
			}
			repaint();

		}
	}

	//pressing keys
	@Override
	public void keyPressed(KeyEvent e) {
		timer1.start();
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_RIGHT) {
			right();
		}
		if (code == KeyEvent.VK_LEFT) {
			left();
		}
		if(code == KeyEvent.VK_ESCAPE) { 
			l3 = 420;
			timer1.stop();
			timer2.stop();
			timer3.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		timer1.stop();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	
	//Paddle movements
	@Override
	public void actionPerformed(ActionEvent e) {

		if (paddleX > 1100) {

			paddleX = 1100;

		} else if (paddleX < 0) {

			paddleX = 0;
		} else {

			paddleX += x1;

		}

		repaint();
	}

	public void right() {
		x1 = 10;

	}

	public void left() {
		x1 = -10;
	}

	// For ball movements and letters, lives falling

	protected class TimerCallBack implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Count2 += 1;

			if (Count2 > 10) {

				Lety1++;
			}

			if (Count2 > 500) {

				Lety2++;
			}

			if (Count2 > 300) {

				Lety3++;
			}

			if (Count2 > 700) {

				Lety4++;
			}

			if (Count2 > 750) {

				Lety5++;
			}

			if (Count2 > 650) {

				ExLifeY++;

			}

			if (Count2 > 950) {

				Lety6++;
			}

			if (Count2 > 1450) {

				Lety7++;
			}

			if (Count2 > 1700) {

				Lety10++;
			}

			if (Count2 > 2200) {

				Lety9++;
			}

			if (Count2 > 2900) {

				Lety8++;
			}

			if (Count2 > 2600) {

				ExLifeY2++;

			}

			ySpeed = bSpeed + G * (time - aVar);
			xSpeed = j * speed * Math.cos(angle);
			ballX = startX + xSpeed * (time - d);
			ballY = startY + ySpeed * (time - aVar) - (0.5 * G * Math.pow(time - aVar, 2));
			time += deltaTime;
			bR = (int) (Math.random() * 255);
			bG = (int) (Math.random() * 255);
			bB = (int) (Math.random() * 255);
			repaint();
			if (new Rectangle((int) ballX, (int) ballY, 50, 50).intersects(new Rectangle(paddleX, 700, 100, 10))) {
				sound.popSound();
				aVar = time;
				bSpeed = -ySpeed;
				startY = 650;
				score++;
			}

			if (ballY > 680) {
				sound.popSound();
				aVar = time;
				bSpeed = -ySpeed;
				startY = 679;
			}

			if (ballX > 1150) {
				sound.popSound();
				startX = 1150;
				j = -1;
				d = time;
			}

			if (ballX < 25) {
				sound.popSound();
				startX = 26;
				j = 1;
				d = time;
			}
			if (ballY < 25) {
				sound.popSound();
				aVar = time;
				bSpeed = -ySpeed;
				startY = 26;
			}

			repaint();
		}

	}

}
