package Ejercicio1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            InetAddress direccion = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            String mensaje = "";
            String mensajeRecibido ="";
            int cont = 0;
            while (!mensajeRecibido.contains("iguales")) {
                System.out.println("Ingresa un numero");
                mensaje = sc.nextLine();
                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 42500);
                socket.send(packet);


                byte[] bufferRecibir = new byte[64];
                DatagramPacket packetRecibir = new DatagramPacket(bufferRecibir, bufferRecibir.length);
                socket.receive(packetRecibir);
                mensajeRecibido =  new String(packetRecibir.getData());
                System.out.println(mensajeRecibido.trim());

            }


        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
