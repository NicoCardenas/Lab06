package aplicacion;

import java.awt.Color;

public class Deportista extends Persona implements EnSalon{
	
    private Salon salon;   
	protected String palabras;
    protected int paso;
    
    public Deportista(){
    	super();
    }
    
	public Deportista(Salon salon,String nombre,int posicionx, int posiciony){
	    super(nombre,posicionx,posiciony);
	    this.salon=salon;
		color=Color.BLACK;
		palabras="Soy"+nombre;
		salon.adicione(this);
		paso=0;
	}


	private boolean puedeMover(char direccion) {
		boolean puede=false;
		int posX = getPosicionX();
		int posY = getPosicionY();
		switch(direccion){
			case 'N' : puede = (posY+1 < salon.MAXIMO);
			break;
			case 'E' : puede = (posX+1 < salon.MAXIMO);
			break;
			case 'S' : puede = (posY-1 >= 0);
			break;
			case 'O': puede = (posX-1 >= 0);
			break; 
		} 
		return puede;
	}
	
	
	public void pare(){
		muevaBrazo('I','B'); 
		muevaPierna('I','P');
		muevaBrazo('D','B'); 
		muevaPierna('D','P');		
		palabras="¡Uff!";
	}



	public void inicie(){
	    palabras="";
        paso++;
		if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
			muevaBrazo('I','S'); 
			muevaPierna('I','S');
		} else if  (getPosicionBrazo('I')==FRENTE){
			muevaBrazo('I','S'); 
			muevaPierna('I','S');
		} else if (getPosicionBrazo('I')==ARRIBA){
			muevaBrazo('I','B'); 
			muevaPierna('I','B');
			muevaBrazo('I','B'); 
			muevaPierna('I','B');
			muevaBrazo('D','S'); 
			muevaPierna('D','S');
		}else if (getPosicionBrazo('D')==FRENTE){
			muevaBrazo('D','S'); 
			muevaPierna('D','S');
			muevaBrazo('D','S'); 
			muevaPierna('D','S');
			muevaBrazo('I','B'); 
			muevaPierna('I','B');
		}else if (getPosicionBrazo('D')==ARRIBA){
			muevaBrazo('D','B'); 
			muevaPierna('D','B');
			muevaBrazo('D','B'); 
			muevaPierna('D','B');
			muevaBrazo('I','S'); 
			muevaPierna('I','S');
		}	
		char direccion=( (paso % 2 == 0)  ? 'E':'O');
		if (puedeMover(direccion)){
		    muevase(direccion);
		}
	}

	
	public String mensaje(){
		return  palabras;
	}

}

