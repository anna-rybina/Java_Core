package ru.netology.network;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Локальные переменные вместо глобальных
        final String host = "localhost";
        final int port = 8089;
        final String message = "Привет от клиента!";

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Подключено к серверу на порту " + port);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(message);
            System.out.println("Отправлено серверу: " + message);

            String serverResponse = in.readLine();
            System.out.println("Ответ сервера: " + serverResponse);

        } catch (UnknownHostException e) {
            System.err.println("Ошибка: хост " + host + " не найден.");
        } catch (IOException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }
}