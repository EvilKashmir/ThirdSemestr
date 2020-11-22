package chat.server;

import chat.client.ClientHandler;
import chat.utils.Constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static List<ClientHandler> clientSocket = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Constant.PORT);
        Socket socket;
        System.out.println("Server started\n");
        while (true) {
            socket = serverSocket.accept();
            System.out.println("New client request received : " + socket);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Creating a new handler for this client...");
            ClientHandler client = new ClientHandler(socket, "client " + count, in, out);
            Thread thread = new Thread(client);
            System.out.println("Adding this client to active client list\n");
            clientSocket.add(client);
            thread.start();
            count++;
        }
    }

    public static List<ClientHandler> getClientSocket() {
        return clientSocket;
    }
}