package Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(49200);
            InputStream is;
            BufferedReader br;
            InputStreamReader ir;
            String mensajeRecibido;
            Socket peticion;

            while (true) {
                peticion = servidor.accept();
                is = peticion.getInputStream();
                ir = new InputStreamReader(is);
                br = new BufferedReader(ir);

                mensajeRecibido = br.readLine();
                new Gestor(mensajeRecibido, peticion).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
