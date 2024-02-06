/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FabricaSuerter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class ProcesoManga extends Thread {
    
    Suerter unSueter;
    int cantManga;
    public ProcesoManga(Suerter unSueter,int cantManga){
    this.cantManga=cantManga;
    this.unSueter=unSueter;
    
    }
    
    
    public void run(){
        int cantidad=0;
        while(cantidad<this.cantManga){
        try {
            //ejecuta manga
            this.unSueter.procesoMangas();
            cantidad++;
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcesoManga.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    
    }
    
}
