package ma.oujda.ump.ensao.autogo;

public class AutoToArduino {

	private char[] ch;
	
	public AutoToArduino(){
		Traitement.mv.initDefaultParamGlobal();
	}

	public void sendToArduino() throws InterruptedException {

		ch = AStarExecute.commandes.toCharArray();

		for (int i = 0; i < ch.length; i++) {

			if (ch[i] == '8') {
				Traitement.mv.goForward();
			} else if (ch[i] == '4') {
				Traitement.mv.goLeft();
			} else if (ch[i] == '6') {
				Traitement.mv.goRight();
			}
		}
	}
}
