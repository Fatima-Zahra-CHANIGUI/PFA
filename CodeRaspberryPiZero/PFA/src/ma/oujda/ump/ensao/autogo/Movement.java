package ma.oujda.ump.ensao.autogo;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class Movement {

	private GpioController gpioController;
	static GpioPinDigitalOutput pinOut1;
	static GpioPinDigitalOutput pinOut2;
	static GpioPinDigitalOutput pinOut3;
	private GpioPinDigitalOutput ORDER_COMPLETED;
	private GpioPinDigitalOutput v;
	int[] paramGlobal = new int[12];
	private int PARAM_VITES_PAS = 0;
	private int PARAM_I_FOR_GO_LEFT = 1;
	private int PARAM_DELAY_GO_LEFT = 2;
	private int PARAM_STOP_GO_LEFT = 3;
	private int PARAM_I_FOR_GO_RIGHT = 4;
	private int PARAM_DELAY_GO_RIGHT = 5;
	private int PARAM_STOP_GO_RIGHT = 6;
	private int PARAM_ANNUL_TEST = 7;
	private int PARAM_I_FOR_GO_FORWARD = 8;
	private int PARAM_CORRECTION = 9;
	private int PARAM_VITES_L = 10;
	private int PARAM_VITES_R = 11;

	public Movement() 
	{
		gpioController = GpioFactory.getInstance();
		pinOut1 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00);
		pinOut2 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02);
		pinOut3 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03);
		ORDER_COMPLETED = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05);
		v = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_25);
	}

	void initDefaultParamGlobal() 
	{
		paramGlobal[PARAM_VITES_PAS] = Traitement.testObjectServer.getPARAM_VITES_PAS();
		paramGlobal[PARAM_I_FOR_GO_LEFT] = Traitement.testObjectServer.getPARAM_I_FOR_GO_LEFT();
		paramGlobal[PARAM_DELAY_GO_LEFT] = Traitement.testObjectServer.getPARAM_DELAY_GO_LEFT();
		paramGlobal[PARAM_STOP_GO_LEFT] = Traitement.testObjectServer.getPARAM_STOP_GO_LEFT();
		paramGlobal[PARAM_I_FOR_GO_RIGHT] = Traitement.testObjectServer.getPARAM_I_FOR_GO_RIGHT();
		paramGlobal[PARAM_DELAY_GO_RIGHT] = Traitement.testObjectServer.getPARAM_DELAY_GO_RIGHT();
		paramGlobal[PARAM_STOP_GO_RIGHT] = Traitement.testObjectServer.getPARAM_STOP_GO_RIGHT();
		paramGlobal[PARAM_ANNUL_TEST] = Traitement.testObjectServer.getPARAM_ANNUL_TEST();
		paramGlobal[PARAM_I_FOR_GO_FORWARD] = Traitement.testObjectServer.getPARAM_I_FOR_GO_FORWARD();
		paramGlobal[PARAM_CORRECTION] = Traitement.testObjectServer.getPARAM_CORRECTION();
		paramGlobal[PARAM_VITES_L] = Traitement.testObjectServer.getPARAM_VITES_L();
		paramGlobal[PARAM_VITES_R] = Traitement.testObjectServer.getPARAM_VITES_R();
	}

	void move(String s) 
	{
			ORDER_COMPLETED.low();
			switch (s) {
				case "000": // stop
				v.low();
				pinOut1.low();
				pinOut2.low();
				pinOut3.low();
				System.out.println("pinout1 : "+pinOut1.getState());
				System.out.println("pinout2 : "+pinOut2.getState());
				System.out.println("pinout3 : "+pinOut3.getState());
				break;
			case "001": // right
				v.low();
				pinOut1.low();
				pinOut2.low();
				pinOut3.high();
				System.out.println("pinout1 : "+pinOut1.getState());
				System.out.println("pinout2 : "+pinOut2.getState());
				System.out.println("pinout3 : "+pinOut3.getState());
				break;
			case "100": // left
				pinOut1.high();
				pinOut2.low();
				pinOut3.low();
				v.high();
				System.out.println("pinout1 : "+pinOut1.getState());
				System.out.println("pinout2 : "+pinOut2.getState());
				System.out.println("pinout3 : "+pinOut3.getState());
				break;
			case "010": // forward
				v.low();
				pinOut1.low();
				pinOut2.high();
				pinOut3.low();
				System.out.println("pinout1 : "+pinOut1.getState());
				System.out.println("pinout2 : "+pinOut2.getState());
				System.out.println("pinout3 : "+pinOut3.getState());
				break;
			case "111": // backward
				v.low();
				pinOut1.high();
				pinOut2.high();
				pinOut3.high();
				System.out.println("pinout1 : "+pinOut1.getState());
				System.out.println("pinout2 : "+pinOut2.getState());
				System.out.println("pinout3 : "+pinOut3.getState());
				break;
		}
		ORDER_COMPLETED.high();
	}

	public void goRight() throws InterruptedException {
		move("000"); Thread.sleep(1000);
		for (int i = 0; i < paramGlobal[PARAM_I_FOR_GO_RIGHT]; i++) {
			move("001"); Thread.sleep(paramGlobal[PARAM_DELAY_GO_RIGHT]);
			move("000"); Thread.sleep(paramGlobal[PARAM_STOP_GO_RIGHT]);
		}
		move("000"); Thread.sleep(1000);
	}

	public void goLeft() throws InterruptedException {
		move("000"); Thread.sleep(1000);
		for (int i = 0; i < paramGlobal[PARAM_I_FOR_GO_LEFT]; i++) {
			move("100"); Thread.sleep(paramGlobal[PARAM_DELAY_GO_LEFT]);
			move("000"); Thread.sleep(paramGlobal[PARAM_STOP_GO_LEFT]);
		}
		move("000"); Thread.sleep(1000);
	}

	void goForward() throws InterruptedException {
		//move("100"); Thread.sleep(paramGlobal[PARAM_CORRECTION]);
		move("000"); Thread.sleep(500);
		for (int i = 0; i < paramGlobal[PARAM_I_FOR_GO_FORWARD]; i++) {
			move("010"); Thread.sleep(paramGlobal[PARAM_VITES_PAS]);
		}
		move("000"); Thread.sleep(1000);
	}

	void goBack() throws InterruptedException {
		move("000"); Thread.sleep(500);
		for (int i = 0; i < paramGlobal[PARAM_I_FOR_GO_FORWARD]; i++) {
			move("111"); Thread.sleep(paramGlobal[PARAM_VITES_PAS]);
		}
		move("000"); Thread.sleep(1000);
	}

	void stop() {
		move("000");
	}

}
