package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {
    public static void main(String args[]){
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            byte[] reciveData= new byte[1024];
            DatagramPacket recibePaquetes= new DatagramPacket(reciveData,reciveData.length);
            socket.receive(recibePaquetes);

            String mensaje=new String(recibePaquetes.getData(),0,recibePaquetes.getLength());
            int numMax=Integer.parseInt(mensaje);
            ArrayList<Integer>listaNumeros=new ArrayList<>();
            for (int i=2;i<=numMax;i++){
                Boolean primo=true;
                for (int j=2; j<=Math.sqrt(i);j++){
                    if (i%j==0){
                        primo=false;
                        break;
                    }

                }
                if (primo){
                    listaNumeros.add(i);
                }
            }
            String numeros= listaNumeros.toString();
            byte[]sendData=numeros.getBytes();
            InetAddress clientIP=recibePaquetes.getAddress();
            int clientPort= recibePaquetes.getPort();
            DatagramPacket enviarPaquete=new DatagramPacket(sendData, sendData.length,clientIP,clientPort);
            socket.send(enviarPaquete);


        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
