/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImpresoraRemota;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class ProcesoCanalPrimario extends Thread {

    boolean corte;
    Impresora unaImpresora;

    public ProcesoCanalPrimario(Impresora unaImpresora) {
        this.corte = false;
        this.unaImpresora = unaImpresora;

    }

    @Override
    public void run() {
        Random random = new Random();
        while (!this.corte) {
            try {

                int randomMilliseconds = random.nextInt(1500) + 500;
                this.sleep(randomMilliseconds);
                this.corte = this.unaImpresora.canalPrimario();

            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoCanalPrimario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
   
        System.out.println("Proceso Primeario estado: finalizado");

    }
}
