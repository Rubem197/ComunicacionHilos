package Ejercicio2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            InetAddress direccionLocal = InetAddress.getLocalHost();
            Socket cliente = new Socket(direccionLocal, 49200);
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            System.out.println("Introduce una direccion");
            String direccion = sc.nextLine();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            osw.write(direccion);
            bw.newLine();
            bw.flush();

            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            System.out.println("El servidor me envía el siguiente mensaje: " + br.readLine());

            is.close();
            os.close();
            cliente.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
