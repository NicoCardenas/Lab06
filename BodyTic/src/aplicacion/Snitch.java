package aplicacion;

import java.awt.Color;

/**
 * Write a description of class DeportistaPerezoso here.
 * 
 * @author Paola Cuellar
 * @author Nicolas Cardenas
 * @version version 1.0 (06/10/2017)
 */
public class Snitch extends Bola{
    
    private int pasos;
    
    /**
     * Constructor for objects of class Snitch
     */
    public Snitch(Salon salon, String nombre, int posicionX, int posicionY){
        super(salon, nombre, posicionX, posicionY);
        color = Color.YELLOW;
        pasos = 0;
    }
    
    public void inicie(){
        if (pasos < 10){
            setPosicion(this.getPosicionX()+10,this.getPosicionY());
            pasos+=1;
        }else if (10 <= pasos && pasos < 20){
            setPosicion(this.getPosicionX()-10,this.getPosicionY());
            pasos+=1;
        }else{ pasos = 0;}
    }
    
    public void pare(){
        inicie();
    }
    
    private void setPosicion(int x, int y){
        this.posicionX = x;
        this.posicionY = y;
    }
}
