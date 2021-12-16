package blackjackfull;

public class Main implements Runnable {
	
	long xTime = System.nanoTime();
	public static boolean terminator = false;
	public static int pWins = 0;
	public static int dWins = 0;
	
	//screen refresh rate
	public int Hz = 100; //this is always running until the program is terminated
	
	GUI gui = new GUI(); //"gui" can be named anything however i only have one GUI
	
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}
	
	@Override
	public void run() {//if the refresh rate is infinate it would lag the system
		while(terminator == false) {
			if (System.nanoTime() - xTime >= 1000000000/Hz) {
				gui.refresher();
				gui.repaint();
				xTime = System.nanoTime();
			}
		}
	}
	
}