package com.elixcore.vallus;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SignalingServer {
    private static final int            PORT  = 12345;
    private static       List<PeerInfo> peers = new ArrayList<>();


    private static final List<Socket> clients = new ArrayList<>();
    private static final List<String> clientEndpoints = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("중개 서버가 시작되었습니다...");
        ServerSocket listener = new ServerSocket(PORT);

        try {
            while (clients.size() < 2) {
                Socket client = listener.accept();
                clients.add(client);

                String endpoint = client.getInetAddress().getHostAddress() + ":" + client.getPort();
                clientEndpoints.add(endpoint);
                System.out.println("클라이언트 연결됨: " + endpoint);
            }

            // 두 클라이언트에게 서로의 정보를 전송
            PrintWriter out1 = new PrintWriter(clients.get(0).getOutputStream(), true);
            PrintWriter out2 = new PrintWriter(clients.get(1).getOutputStream(), true);

            System.out.println("클라이언트 0에게 클라이언트 1의 정보 전송: " + clientEndpoints.get(1));
            out1.println(clientEndpoints.get(1));

            System.out.println("클라이언트 1에게 클라이언트 0의 정보 전송: " + clientEndpoints.get(0));
            out2.println(clientEndpoints.get(0));

        } finally {
            listener.close();
            System.out.println("서버를 종료합니다.");
        }
    }

    private static void sendAddressToPeer(String ip, int port, PeerInfo peerInfoToSend) throws IOException {
        Socket socket = new Socket(ip, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(peerInfoToSend.publicIp + ":" + peerInfoToSend.publicPort);
        socket.close();
    }

    static class PeerInfo {
        String publicIp;
        int publicPort;

        PeerInfo(String publicIp, int publicPort) {
            this.publicIp = publicIp;
            this.publicPort = publicPort;
        }
    }
}