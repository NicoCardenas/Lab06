package aplicacion;

import java.awt.Color;

/**
 * Write a description of class DeportistaPerezoso here.
 * 
 * @author Paola Cuellar
 * @author Nicolas Cardenas
 * @version version 1.0 (06/10/2017)
 */
public class DeportistaPerezoso extends Deportista{

    /**
     * Constructor for objects of class DeportistaPerezoso
     */
    public DeportistaPerezoso(Salon salon,String nombre,int posicionx,int posiciony){
        super(salon, nombre, posicionx, posiciony);
        color = Color.BLUE;
    }
    
    public void inicie(){
        super.inicie();
        super.pare();
        palabras = "Que cansancio";
    }
    
    public void decida(){
        pare();
        palabras = "Que cansancio";
    }
}
