/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImpresoraRemota;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class ProcesoImprimir extends Thread {

    boolean corte;
    Impresora unaImpresora;

    public ProcesoImprimir(Impresora unaImpresora) {
        this.corte = false;
        this.unaImpresora = unaImpresora;

    }

    public void run() {
    
        while (!this.corte) {
            try {
                this.corte = this.unaImpresora.datosImpresos();
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoImprimir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Proceso Imprimir estado: finalizado");

    }

}
