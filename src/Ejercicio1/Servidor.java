package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        Random rnd = new Random();
        int numeroAleatorio = rnd.nextInt(100);
        try {
            socket = new DatagramSocket(42500);
            while (true) {
                byte[] buffer = new byte[64];

                DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);

                socket.receive(datagramaEntrada);

                new Gestor(socket, numeroAleatorio, datagramaEntrada).start();

            }
            // 6 - Cierre del socket
//			System.out.println("(Servidor): Cerrando la conexión...");
//			socket.close();
//			System.out.println("(Servidor): Conexión cerrada");
//
        } catch (SocketException e) {
            System.out.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete");
            e.printStackTrace();
        }
    }
}
