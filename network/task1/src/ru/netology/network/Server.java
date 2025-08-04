package ru.netology.network;

import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 8089;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Новое подключение принято");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientMessage = in.readLine();
            int clientPort = clientSocket.getPort();

            System.out.println("Получено от клиента (порт " + clientPort + "): " + clientMessage);
            out.println("Сервер получил ваше сообщение: '" + clientMessage + "' (порт " + clientPort + ")");

        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }
}