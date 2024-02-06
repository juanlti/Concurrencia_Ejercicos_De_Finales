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
public class ProcesoContorno extends Thread {
    
    int cantManga;
    Suerter unSueter;
    public ProcesoContorno(Suerter unSueter,int cantManga){
        this.cantManga=cantManga;
        this.unSueter=unSueter;
        
    
    }
    
    
    public void run(){
         int cant=0;
    while(cant<cantManga){
    
         try {
            
             this.unSueter.procesoContorno();
             cant++;
             
         } catch (InterruptedException ex) {
             Logger.getLogger(ProcesoContorno.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
    }
    
    
}
