package org.example.secvg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class CreateHashes implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("RUNNING CREATE HASHES");
    }
}
