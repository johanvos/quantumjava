package org.redfx.javaqc.ch06.classic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Main {

    static final int PORT = 9753;

    public static void main(String[] args) throws InterruptedException {
        startReceiver();
        startSender();
    }

    static void startSender() {
        Thread t = new Thread() {
            @Override public void run() {
                try {
                    byte b = 0x8;
                    System.err.println("[Sender] Create a connection to port "+PORT);
                    Socket socket = new Socket("localhost", PORT);
                    OutputStream outputStream = socket.getOutputStream();
                    System.err.println("[Sender] Write a byte: "+b);
                    outputStream.write(b);
                    outputStream.close();
                    System.err.println("[Sender] Wrote a byte: "+b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    static void startReceiver() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread() {
            @Override public void run() {
                try {
                    System.err.println("[Receiver] Starting to listen for incoming data at port "+PORT);
                    ServerSocket serverSocket = new ServerSocket(PORT);
                    latch.countDown();
                    Socket s = serverSocket.accept();
                    InputStream inputStream = s.getInputStream();
                    int read = inputStream.read();
                    System.err.println("[Receiver] Got a byte "+read);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        latch.await();
    }

}
