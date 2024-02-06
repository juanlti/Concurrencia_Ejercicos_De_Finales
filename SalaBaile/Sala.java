/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalaBaile;

import java.util.concurrent.Semaphore;

/**
 *
 * @author juanc
 */
public class Sala {

    private final Semaphore sra, sr, mutexSra, mutexSr, bailar;
 
    
    public Sala() {
        this.mutexSra = new Semaphore(1);
        this.mutexSr = new Semaphore(0);
        this.bailar = new Semaphore(0);

  
        this.sr = new Semaphore(0);
        this.sra = new Semaphore(0);

    }

    public void ingresoSra(String nombre, String nombrePareja) throws InterruptedException {
        //mutexSra

        this.mutexSra.acquire();
        System.out.println("Comienzan : ");


        this.bailar.release();

    }

    public void ingresoSr(String nombre, String nombrePareja) throws InterruptedException {
        //mutexSr
        this.mutexSr.acquire();

        this.bailar.release();

    }

    public void bailar(String nombre, boolean esSra) throws InterruptedException {
  
        this.bailar.acquire();
        if (esSra) {
            this.mutexSr.release();
            this.sra.acquire();
        }

        System.out.println("Bailan " + nombre);

    }

    public void sraFinalizandoBaile(String nombre) throws InterruptedException {
        this.sr.release();

        System.out.println(nombre + " Sra termino de bailar");
        this.mutexSra.release();

    }

    public void srFinalizandoBaile(String nombre) throws InterruptedException {
        this.sra.release();

        this.sr.acquire();

        System.out.println(nombre + " Sr termino de bailar");

    }

}
