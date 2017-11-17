package aplicacion;

import java.awt.Color;

/**
 * Write a description of class DeportistaPerezoso here.
 * 
 * @author Paola Cuellar
 * @author Nicolas Cardenas
 * @version version 1.0 (06/10/2017)
 */
public class Bola implements EnSalon{
    
    private Salon salon;
    private String nombre;
    protected int posicionX;
    protected int posicionY;
    protected Color color;    
    
    public Bola(Salon salon, String nombre, int posicionX,int posicionY){
        this.salon = salon;
        this.nombre = nombre;
        this.color = Color.GRAY;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        salon.adicione(this);       
    }
    
    public void inicie(){
        color = Color.RED;
    }
    
    public void pare(){
        color = Color.GRAY;
    }
    
    public String mensaje(){
        return "";
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getPosicionX(){
        return posicionX;
    }
    
    public int getPosicionY(){
        return posicionY;
    }
}
