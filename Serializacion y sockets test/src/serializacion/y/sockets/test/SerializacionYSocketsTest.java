/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serializacion.y.sockets.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author luren
 */
public class SerializacionYSocketsTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String string = "juan";
        String stringDeserializado = null;
        try{
            FileOutputStream fileOut = new FileOutputStream("stringjuan.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeUTF(string);
            out.close();
            fileOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            FileInputStream fileIn = new FileInputStream("stringjuan.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            stringDeserializado = in.readUTF();
            in.close();
            fileIn.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Socket conexion = new Socket("127.0.0.1", 9999);
            DataOutputStream streamOut = new DataOutputStream(conexion.getOutputStream());
            streamOut.writeUTF(out);
            streamOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            ServerSocket server = new ServerSocket(9999);
            Socket socket = server.accept();
            DataInputStream streamIn = new DataInputStream(socket.getInputStream());
            String recibido = streamIn.readUTF();
            streamIn.close();
            server.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
