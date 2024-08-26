package org.example.secvg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@ComponentScan
public class CreateHashes implements CommandLineRunner {

    String input = "test.dict";
    String output = "hashes.txt";
    String line, hash;
    String api = "https://api.hashify.net/hash/sha256/hex?value=";
    HttpResponse<String> response;
    List<String> lines;
    int totalLines;

    @Override
    public void run(String... args) {

        try {
            lines = new BufferedReader(new FileReader(input)).lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("IO error while reading input file: " + e.getMessage());
        }
        totalLines = lines.size();
        lines.clear();

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
                HttpClient client = HttpClient.newHttpClient()
        ) {
            int i = 0;
            System.out.println("Hashing passwords (total: " + totalLines + ")");
            while ((line = br.readLine()) != null) {
                String uri = api + line;
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                hash = parseHashFromResponse(response.body());
                bw.write(line + ":" + hash + "\n");
                i++;
                printProgress(i, totalLines);
            }
            printProgress(totalLines, totalLines);
            System.out.println("\n" + "All done!");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
        }
    }

    private String parseHashFromResponse(String responseBody) {
        String[] parts = responseBody.split("\"Digest\":\"");
        if (parts.length > 1) {
            return parts[1].split("\"")[0];
        }
        return "unknown";
    }

    private void printProgress(int current, int total) {
        int progressBarWidth = 50;
        double progress = (double) current / total;
        int progressWidth = (int) (progress * progressBarWidth);

        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < progressBarWidth; i++) {
            if (i < progressWidth) {
                progressBar.append("#");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("]");
        System.out.print("\r" + progressBar + " " + (int) (progress * 100) + "% Complete");
    }

}


