package ma.oujda.ump.ensao.autogo;

public class ManToArduino {

	public ManToArduino(){
		Traitement.mv.initDefaultParamGlobal();
	}

	public void sendToArduino(char ch) throws InterruptedException {

		if (ch == '8') // forward 011
		{
			Traitement.mv.goForward();
		} else if (ch == '4') // left 100
		{
			Traitement.mv.goLeft();
		} else if (ch == '6') // right 001
		{
			Traitement.mv.goRight();
		} else if (ch == '2') // backward 110
		{
			Traitement.mv.goBack();
		} else if (ch == '5') // stop 000
		{
			Traitement.mv.stop();
		}
	}
}
