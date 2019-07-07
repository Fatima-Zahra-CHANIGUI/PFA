package ma.oujda.ump.ensao.autogo;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.autoObject;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.sendAutoObject;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.stopAutoMode;
import static ma.oujda.ump.ensao.autogo.ConnectActivity.stopThread;
import static ma.oujda.ump.ensao.autogo.ManuelActivity.manBwd;
import static ma.oujda.ump.ensao.autogo.ManuelActivity.manFwd;
import static ma.oujda.ump.ensao.autogo.ManuelActivity.manLeft;
import static ma.oujda.ump.ensao.autogo.ManuelActivity.manRight;
import static ma.oujda.ump.ensao.autogo.ManuelActivity.manStop;
import static ma.oujda.ump.ensao.autogo.ManuelActivity.stopManuelMode;
import static ma.oujda.ump.ensao.autogo.ModeActivity.mode;
import static ma.oujda.ump.ensao.autogo.TestActivity.sendTestObject;
import static ma.oujda.ump.ensao.autogo.TestActivity.stopTestMode;
import static ma.oujda.ump.ensao.autogo.TestActivity.testObject;

public class ClientConnexion implements Runnable{

    static Socket socket ;
    private String adr;
    private int port;
    private ObjectOutputStream oos ;
    private ObjectInputStream ois;
    private BufferedWriter os = null;
    private BufferedReader is = null ;
    private String line;
    static AutoObject autoObjectServer = new AutoObject();

    ClientConnexion(String adr , int port){
        this.adr=adr;
        this.port=port;
    }

    public void Connexion(){
        try {
            socket = new Socket(adr,port);
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    ConnectActivity.text.setText("Vous êtes connecté au Raspberry Pi Zero !");
                    ConnectActivity.connectButton.setText("Se déconnecter");
                    ConnectActivity.nextButton.setVisibility(View.VISIBLE);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        try
        {
            Connexion();

            os= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(stopThread==false){

                if(mode.equals("manuel")){
                    os.write("ManuelMode");
                    os.newLine();
                    os.flush();

                    while (stopManuelMode==false){
                        if(manFwd) {
                            os.write("forward");
                            os.newLine();
                            os.flush();
                            manFwd=false;
                        }
                        if(manLeft) {
                            os.write("left");
                            os.newLine();
                            os.flush();
                            manLeft=false;
                        }
                        if(manRight) {
                            os.write("right");
                            os.newLine();
                            os.flush();
                            manRight=false;
                        }
                        if(manBwd) {
                            os.write("backward");
                            os.newLine();
                            os.flush();
                            manBwd=false;
                        }
                        if(manStop) {
                            os.write("stop");
                            os.newLine();
                            os.flush();
                            manStop=false;
                        }
                        /*
                        line = is.readLine();
                        if(line!=null && line.equals("notAvailable")){
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ManuelActivity.manTextView.setText("Attendez l'arrêt de la voiture !");
                                }
                            });
                        }
                        if(line!=null && line.equals("forward")){
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ManuelActivity.manTextView.setText("Avancer ...");
                                }
                            });
                        }
                        if(line!=null && line.equals("right")){
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ManuelActivity.manTextView.setText("Droite ...");
                                }
                            });
                        }
                        if(line!=null && line.equals("left")){
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ManuelActivity.manTextView.setText("Gauche ...");
                                }
                            });
                        }
                        if(line!=null && line.equals("backward")){
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ManuelActivity.manTextView.setText("Reculer ...");
                                }
                            });
                        }
                        if(line!=null && line.equals("stop")){
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ManuelActivity.manTextView.setText("Stop");
                                }
                            });
                        }
                        */
                    }
                    stopManuelMode=true;
                    os.write("stopManuelMode");
                    os.newLine();
                    os.flush();
                    mode="";
                }

                if(mode.equals("auto")){
                    os.write("AutoMode");
                    os.newLine();
                    os.flush();

                    while (stopAutoMode==false){

                        if(sendAutoObject){
                            os.write("sendAutoObject");
                            os.newLine();
                            os.flush();
                            oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(autoObject);
                            oos.flush();
                            Thread.sleep(4000);
                            ois = new ObjectInputStream(socket.getInputStream());
                            autoObjectServer= (AutoObject) ois.readObject();
                            sendAutoObject=false;
                        }
                    }

                    stopAutoMode=true;
                    os.write("stopAutoMode");
                    os.newLine();
                    os.flush();
                    mode="";
                }

                if(mode.equals("test")){
                    os.write("TestMode");
                    os.newLine();
                    os.flush();

                    while (stopTestMode==false){

                        if(sendTestObject){
                            os.write("sendTestObject");
                            os.newLine();
                            os.flush();
                            oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(testObject);
                            oos.flush();
                            sendTestObject=false;
                        }
                    }

                    stopTestMode=true;
                    os.write("stopTestMode");
                    os.newLine();
                    os.flush();
                    mode="";
                }

            }

            os.write("Disconnect");
            os.newLine();
            os.flush();
            Thread.sleep(1000);
            socket.close();
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    ConnectActivity.text.setText("Vous êtes déconnecté !");
                    ConnectActivity.connectButton.setText("Se connecter");
                    ConnectActivity.nextButton.setVisibility(View.INVISIBLE);
                }
            });

        } catch (InterruptedException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}