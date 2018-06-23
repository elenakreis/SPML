/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment4b;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Elena
 */
public class MyWriter {

    private Writer writer;

    public MyWriter(String fileName) {
        try {
            writer = new FileWriter(String.format("C:\\Users\\Elena\\Documents\\Uni\\Search, Planning & Machine Learning\\A4 test data\\%s.xls",fileName));

            
        } catch (IOException e) {
        }

    }

    public void write(double[] array) {
        try {
            for (int i = 0; i < array.length; i++) {
                double d = array[i];
                writer.write(String.format("%d\t%f%n", i,d));
                
            }
            writer.close();
        } catch (IOException e) {
        }
    }
}
