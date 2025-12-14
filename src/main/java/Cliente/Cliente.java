package Cliente;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String args[]){
        Scanner teclado= new Scanner(System.in);
        try {
            DatagramSocket datagramSocket=new DatagramSocket();
            InetAddress addres=InetAddress.getByName("localHost");
            int serverPort= 12345;

            System.out.println("introduce que numero quieres ver los primos que hay hasta el");
            String resp = String.valueOf(teclado.nextInt());
            teclado.nextLine();
            byte[] datos=resp.getBytes();

            DatagramPacket enviarPaquete=new DatagramPacket(datos,datos.length,addres,serverPort);
            datagramSocket.send(enviarPaquete);

            byte[]recibe=new byte[1024];
            DatagramPacket recibirPaquete= new DatagramPacket(recibe,recibe.length);
            datagramSocket.receive(recibirPaquete);
            String mensaje=new String(recibirPaquete.getData(),0,recibirPaquete.getLength());
            System.out.println("los numeros primos que hay del 0 al " +resp+" son "+mensaje);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
