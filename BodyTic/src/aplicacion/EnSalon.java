package aplicacion;
import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

public interface EnSalon extends Serializable{

    Random r = new Random(1);
    
    int getPosicionX();
    int getPosicionY();
    Color getColor();
    String mensaje();

    
    void inicie();
    void pare();
    
    default void decida(){
        if (r.nextBoolean()){
            inicie();
        }else{
            pare();
        }
    }
}
