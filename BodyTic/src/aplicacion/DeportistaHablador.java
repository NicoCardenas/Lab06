package aplicacion;

import java.awt.Color;

/**
 * Write a description of class DeportistaPerezoso here.
 * 
 * @author Paola Cuellar
 * @author Nicolas Cardenas
 * @version version 1.0 (06/10/2017)
 */
public class DeportistaHablador extends Deportista{            
    
    /**
     * Constructor for objects of class DeportistaHablador
     */
    public DeportistaHablador(Salon salon,String nombre,int posicionx,int posiciony){
        super(salon, nombre, posicionx, posiciony);
        color = Color.RED;
    }
    
    public void inicie(){
        super.inicie();
        palabras = "hacer deporte es bueno";
    }
    
    public void pare(){
        super.pare();
        palabras = "Uff! esto es agotador, necesito agua";
    }
}
