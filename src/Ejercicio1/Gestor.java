package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class Gestor extends Thread {
    DatagramSocket socket;
    int numeroAleatorio;
    DatagramPacket datagramaEntrada;


    public Gestor(DatagramSocket socket, int numeroAleatorio, DatagramPacket datagramaEntrada) {
        super();
        this.socket = socket;
        this.numeroAleatorio = numeroAleatorio;
        this.datagramaEntrada = datagramaEntrada;
    }

    @Override
    public void run() {
        realizarProceso();
    }

    public void realizarProceso() {
        try {
            InetAddress direccion = InetAddress.getLocalHost();

            String mensajeRecibido = new String(datagramaEntrada.getData()).trim();
            String textoEnvia = "";
            if (Integer.parseInt(mensajeRecibido) < numeroAleatorio) {
                textoEnvia = "el numero es mayor";
            } else if (Integer.parseInt(mensajeRecibido) > numeroAleatorio) {
                textoEnvia = "el numero es menor";
            } else {
                textoEnvia = "son iguales";
            }

            // 5 - Generación y envío de la respuesta
            System.out.println("(Servidor): Enviando datagrama...");
            byte[] mensajeEnviado = textoEnvia.getBytes();
            DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length,
                    datagramaEntrada.getAddress(), datagramaEntrada.getPort());

            socket.send(packetSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
