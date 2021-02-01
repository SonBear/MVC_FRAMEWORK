/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author emman
 */
public class ManagerFiles {

    public String readFile(String path) {
        String res = "";
        try {

            Scanner fileReader = new Scanner(new File(path));

            while (fileReader.hasNext()) {

                res += fileReader.nextLine() + "\n";

            }
            fileReader.close();
        } catch (FileNotFoundException ex) {
        }
        return res;
    }

    public void writeFile(String path, String content) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {

        }
    }

    public double getFileSizeGigaBytes(File file) {
        return getFileSizeMegaBytes(file) / 1024;
    }

    public double getFileSizeMegaBytes(File file) {
        return getFileSizeKiloBytes(file) / 1024;
    }

    public double getFileSizeKiloBytes(File file) {
        return file.length() / (1024);
    }

}
