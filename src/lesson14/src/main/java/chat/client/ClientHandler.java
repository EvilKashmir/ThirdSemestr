package chat.client;

import chat.server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {

    private final String name;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final Socket socket;
    private boolean status;

    public ClientHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos) {
        this.in = dis;
        this.out = dos;
        this.name = name;
        this.socket = socket;
        this.status = true;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                received = in.readUTF();
                System.out.println(received);
                if (received.equals("logout")) {
                    this.status = false;
                    this.socket.close();
                    break;
                }
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                System.out.println(st.countTokens());
                if (st.countTokens() > 0) {
                    String recipient = st.nextToken();
                    for (ClientHandler mc : Server.getClientSocket()) {
                        if (mc.name.equals(recipient) && mc.status) {
                            mc.out.writeUTF(this.name + " : " + MsgToSend);
                            break;
                        }
                    }
                } else {
                    Server.getClientSocket().forEach(clientHandler -> {
                        try {
                            if (!clientHandler.name.equals(this.name)) {
                                clientHandler.out.writeUTF(this.name + " : " + MsgToSend);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.in.close();
            this.out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}