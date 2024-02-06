/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FabricaSuerter;

/**
 *
 * @author juanc
 */public class Test {
  public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        Suerter unaFabrica = new Suerter();
        int cantidadDeSueters =17;
        ProcesoArmado unPA = new ProcesoArmado(unaFabrica, cantidadDeSueters);
        ProcesoManga unPM = new ProcesoManga(unaFabrica, cantidadDeSueters * 2);
        ProcesoContorno unPC = new ProcesoContorno(unaFabrica, cantidadDeSueters);

        unPM.start();
        unPC.start();
        unPA.start();
        unPM.join();
        unPC.join();
        unPA.join();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double elapsedTimeSeconds = (double) elapsedTime / 1_000_000_000.0;
        System.out.println("El tiempo transcurrido para construir " + cantidadDeSueters + " sueters, es de " + elapsedTimeSeconds + " segundos");

    }

 }
