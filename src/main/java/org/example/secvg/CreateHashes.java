package org.example.secvg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@ComponentScan
public class CreateHashes implements CommandLineRunner {

    BCryptHashing bCrypt = new BCryptHashing();
    String input = "full.dict";
    String output = "hashes.txt";
    String line, hash;
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
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)))
        ) {
            int i = 0;
            System.out.println("Hashing passwords (total: " + totalLines + ")");
            while ((line = br.readLine()) != null) {
                hash = bCrypt.hashPassword(line);
                if (bCrypt.isHashCorrect(line, hash)) {
                    bw.write(line + ":" + hash + "\n");
                    i++;
                }
                printProgress(i, totalLines);
            }
            printProgress(totalLines, totalLines);
            System.out.println("\n" + "All done!");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
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


