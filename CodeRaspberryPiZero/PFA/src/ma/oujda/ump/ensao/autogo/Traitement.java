package ma.oujda.ump.ensao.autogo;

import java.io.*;
import java.net.Socket;

public class Traitement implements Runnable {

	private Socket sock;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private BufferedReader is;
	private String line;
	private AutoObject autoObjectClient;
	private AutoObject autoObjectServer;
	private ManToArduino manToArduino;
	private AutoToArduino autoToArduino;
	private TestObject testObjectClient;
	static Movement mv= new Movement();
	//static TestObject testObjectServer = new TestObject( 78 , 40 , 48 , 70 , 43 , 46 , 70 , 0 , 13 , 0 , 253 , 207 );
	//static TestObject testObjectServer = new TestObject( 1000 , 3 , 1000 , 1000 , 3 , 1000 , 1000 , 0 , 3 , 0 , 200 , 200 );
	static TestObject testObjectServer = new TestObject( 755 , 1 , 495 , 495 , 1 , 755 , 755 , 0 , 1 , 0 , 200 , 200 );
	static volatile Boolean StopAutoMode = true;
	static volatile Boolean StopManuelMode = true;
	static volatile Boolean StopTestMode = true;

	public Traitement(Socket pSock) 
	{
		sock = pSock;
	}

	public void run() 
	{

		System.out.println("** Lancement du traitement de la connexion cliente **");

		
		
		try 
		{
			while (true) 
			{
				is = new BufferedReader(new InputStreamReader(sock.getInputStream()));

//				R�cup�rer les donn�es envoy�es par le client
				line = is.readLine();

//				Le client souhaite se d�connecter
				if (line != null && line.equals("Disconnect")) {
					break;
				}

//				Le client a choisi le mode Manuel
				if (line != null && line.equals("ManuelMode")) {
					StopManuelMode = false;
					StopAutoMode = true;
					StopTestMode = true;
					System.out.println("-----------Mode Manuel : START !");
					manToArduino = new ManToArduino();
				}

//				Le client a choisi le mode automatique
				if (line != null && line.equals("AutoMode")) {
					StopAutoMode = false;
					StopManuelMode = true;
					StopTestMode = true;
					System.out.println("-----------Mode Automatique : START !");
					autoToArduino = new AutoToArduino();
				}
				
//				Le client a choisi le mode de test
				if (line != null && line.equals("TestMode")) {
					StopTestMode = false;
					StopAutoMode = true;
					StopManuelMode = true;
					System.out.println("-----------Mode de Test : START !");
				}
				
// ********************************** Traitement de la demande en mode Manuel ***************************************
				
//				Tant que le client est dans le mode Manuel	
				while (StopManuelMode == false)						
				{
					line = is.readLine();
					switch (line) 
					{
						case "forward":
							System.out.println("Avancer");
							manToArduino.sendToArduino('8');
							break;
						case "right":
							System.out.println("Droite");
							manToArduino.sendToArduino('6');
							break;
						case "left":
							System.out.println("Gauche");
							manToArduino.sendToArduino('4');
							break;
						case "backward":
							System.out.println("Reculer");
							manToArduino.sendToArduino('2'); 
							break;
						case "stop":
							System.out.println("Stop");
							manToArduino.sendToArduino('5');
							break;
						case "stopManuelMode":
//							Si le client quitte le mode Manuel
							System.out.println("-----------Mode Manuel : END !");
							StopManuelMode = true;
							break;
					}
				}//fin while
				
// ********************************** Traitement de la demande en mode Automatique ***************************************

//				Tant que le client est dans le mode automatique
				while (StopAutoMode == false)
				{
					line = is.readLine();
					
//					Si le client envoi un objet auto
					if (line.equals("sendAutoObject"))
					{
						Thread.sleep(500);
						ois = new ObjectInputStream(sock.getInputStream());
						oos = new ObjectOutputStream(sock.getOutputStream());

//						On r�cup�re l'objet envoy� par le client
						autoObjectClient = (AutoObject) ois.readObject();

//						On le clone dans un objet serveur
						autoObjectServer = new AutoObject(autoObjectClient.getRows(), autoObjectClient.getColumns(),
								autoObjectClient.getD(), autoObjectClient.getF(), autoObjectClient.getBlock());

//						On calcule A*
						AStarExecute AstarExe = new AStarExecute(autoObjectServer.getRows(),autoObjectServer.getColumns(),
								autoObjectServer.getD(), autoObjectServer.getF(),autoObjectServer.getBlock());

//						On r�cup�re le chemin calcul� et on l'ajoute dans l'objet qu'on va envoyer au client
						autoObjectServer.setPath(AstarExe.getPath());

//						On envoi le r�sultat au client
						oos.writeObject(autoObjectServer);
						oos.flush();

//						On affiche le r�sultat au niveau du serveur
						System.out.println(autoObjectServer.toString());
						
//						Envoyer le r�sultat � l'arduino pour executer
			            autoToArduino.sendToArduino();
					}
//					Si le client quitte le mode Automatique
					else if (line.equals("stopAutoMode")) {
						System.out.println("-----------Mode Automatique : END !");
						StopAutoMode = true;
						break;
					}
				}//fin while

// ********************************** Traitement de la demande en mode de Test ***************************************

//				Tant que le client est dans le mode Test
				while (StopTestMode == false) 
				{
					line = is.readLine();
					
//					Si le client envoi un objet Test
					if (line.equals("sendTestObject")) 
					{
						Thread.sleep(1000);
						ois = new ObjectInputStream(sock.getInputStream());
						oos = new ObjectOutputStream(sock.getOutputStream());

						// On r�cup�re l'objet envoy� par le client
						testObjectClient = (TestObject) ois.readObject();

						// On le clone dans un objet serveur
						testObjectServer = new TestObject(testObjectClient.getPARAM_VITES_PAS(),
								testObjectClient.getPARAM_I_FOR_GO_LEFT(), testObjectClient.getPARAM_DELAY_GO_LEFT(),
								testObjectClient.getPARAM_STOP_GO_LEFT(), testObjectClient.getPARAM_I_FOR_GO_RIGHT(),
								testObjectClient.getPARAM_DELAY_GO_RIGHT(), testObjectClient.getPARAM_STOP_GO_RIGHT(),
								testObjectClient.getPARAM_ANNUL_TEST(), testObjectClient.getPARAM_I_FOR_GO_FORWARD(),
								testObjectClient.getPARAM_CORRECTION(), testObjectClient.getPARAM_VITES_L(),
								testObjectClient.getPARAM_VITES_R());

						// On affiche les donn�es envoy�es au niveau du serveur
						System.out.println(testObjectServer.getPARAM_VITES_PAS());
						System.out.println(testObjectServer.getPARAM_I_FOR_GO_LEFT());
						System.out.println(testObjectServer.getPARAM_DELAY_GO_LEFT());
						System.out.println(testObjectServer.getPARAM_STOP_GO_LEFT());
						System.out.println(testObjectServer.getPARAM_I_FOR_GO_RIGHT());
						System.out.println(testObjectServer.getPARAM_DELAY_GO_RIGHT());
						System.out.println(testObjectServer.getPARAM_STOP_GO_RIGHT());
						System.out.println(testObjectServer.getPARAM_ANNUL_TEST());
						System.out.println(testObjectServer.getPARAM_I_FOR_GO_FORWARD());
						System.out.println(testObjectServer.getPARAM_CORRECTION());
						System.out.println(testObjectServer.getPARAM_VITES_L());
						System.out.println(testObjectServer.getPARAM_VITES_R());
					}
//					Si le client quitte le mode de Test
					else if (line.equals("stopTestMode")) 
					{
						System.out.println("-----------TestMode end !");
						StopTestMode = true;
						break;
					}
				}//fin while
			}//fin while
			sock.close();
			System.out.println("Client Déconnecté !");
			
		} catch (IOException | ClassNotFoundException | InterruptedException e ) {e.printStackTrace();}
	}//fin run()
}
