/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalaBaile;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class SrBailarin extends Thread {

    Sala unaSala;
    String nombre, nombrePareja;

    public SrBailarin(String nombre, Sala unaSala, String nombrePareja) {
        this.unaSala = unaSala;
        this.nombre = nombre;
        this.nombrePareja = nombrePareja;

    }

    public void run() {

        try {
            //tiempo
            this.unaSala.ingresoSr(nombre,nombrePareja);
              Random random = new Random();
            int randomMilliseconds = random.nextInt(300) + 100; // Genera un número entre 0 y 3999, luego suma 1000

            Thread.sleep(randomMilliseconds);
            this.unaSala.bailar(nombre,false);
            this.unaSala.srFinalizandoBaile(nombre);

        } catch (InterruptedException ex) {
            Logger.getLogger(SraBailarin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
