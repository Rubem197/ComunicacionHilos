package Ejercicio2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Gestor extends Thread{
    private Socket socket;
    private OutputStream os;
    private OutputStreamWriter osw;
    String mensajeRecibido;

    public Gestor(String mensajeRecibido, Socket socket) {
        this.socket = socket;
        this.mensajeRecibido = mensajeRecibido;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {
        os = this.socket.getOutputStream();
        osw = new OutputStreamWriter(os);

        BufferedWriter bw = new BufferedWriter(osw);
        osw.write(leerFichero(mensajeRecibido));
        bw.newLine();
        bw.flush();
    }

    public static String leerFichero(String direccion) {

        BufferedReader br = null;
        String[] contenido;
        String ip = "";

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\ruben\\IdeaProjects\\ComunicacionHilos\\src\\Ejercicio2\\direcciones"));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {
                contenido = sc.nextLine().split(" ");
                if (contenido[0].contains(direccion)) {
                    ip = contenido[1];
                }
                if (ip.equals("")) {
                    ip = "La direccion no existe";
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ip;
    }
}
