package resources;

public class Main {

	public static void main(String[] args) {

		Solitaire game= new Solitaire();
		GUI gui = new GUI(game);
		//game.initialize();
		gui.update();
		//System.out.println(game.testWin());
		//gui.update();
			
		
	}

	

 
}