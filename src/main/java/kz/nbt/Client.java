package kz.nbt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
	 private static Socket clientSocket; //сокет для общения
	    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
	    // мы узнаем что хочет сказать клиент?
	    private static BufferedWriter out; // поток записи в сокет

	    public  void connect(String msg) {
	        try {
	            try {
	                // адрес - локальный хост, порт - 4004, такой же как у сервера
	                clientSocket = new Socket("localhost", 8081); // этой строкой мы запрашиваем
	                //  у сервера доступ на соединение
	                //reader = new BufferedReader(new InputStreamReader(System.in));
	                // читать соообщения с сервера
	               // in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	                // писать туда же
	                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

	                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
	                // если соединение произошло и потоки успешно созданы - мы можем
	                //  работать дальше и предложить клиенту что то ввести
	                // если нет - вылетит исключение
	                // word = "Сообщение из Rest сервиса"; // ждём пока клиент что-нибудь
	                // не напишет в консоль
	               // out.write(word + "\n"); // отправляем сообщение на сервер
	                Date time = new Date(); // текущая дата
	                SimpleDateFormat dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                    String  dtime = dt1.format(time); // время
	                
	                
	                out.write(msg + "\n"); // отправляем на сервер
	                
	                
	                out.flush();
	               // String serverWord = in.readLine(); // ждём, что скажет сервер
	               // System.out.println(serverWord); // получив - выводим на экран
	            } finally { // в любом случае необходимо закрыть сокет и потоки
	            	
	            	 System.out.println("Клиент был закрыт...");
	                 clientSocket.close();
	                 out.close();
	        
	            }
	        } catch (IOException e) {
	            System.err.println(e);
	        }

	    }
	}