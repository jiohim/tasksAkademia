package pl.jiohim.task3;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

    private final static Set<String> quotes = new HashSet<>();       //create set of quotes, to not be duplicated
    private final static String URL = "https://api.kanye.rest/";

    public static void main(String[] args) {
        runKanyeRestApp();                                //running application
    }


    private static String getKanyeRestQuote() {

        String quote = null;

        try {
            //create connection
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //checking if there is no response code error
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder sb = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());          //append quote object in StringbBilder
                }
                scanner.close();
                quote = sb.toString();
            } else {
                throw new RuntimeException("HTTP response code : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quote;
    }


    private static void print() {
        String q = getKanyeRestQuote();     //receiving quote
        while (quotes.contains(q)) { //checking if quote is already in Set , while we get new quote
            q = getKanyeRestQuote();
        }
        quotes.add(q);
        System.out.println(q.substring(9, q.length() - 1)); //parsing quote and show it in console
        System.out.println("->> Type \"next\" to get new quote or \"exit\" to close application");
    }

    private static void runKanyeRestApp() {
        try {
            String input;                   //input from console

            Scanner console = new Scanner(System.in);
            do {
                print();                    //print first quote
            }
            while ((input = console.nextLine()).equals("next"));

            if (input.equals("exit")) {
                console.close();
            } else {
                System.out.println("Wrong command ,closing application");
            }
        } catch (Exception e) {                         //if all quotes are shown > handling an error
            System.out.println("All quotes are shown");
        }

    }
}



