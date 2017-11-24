package presentacion;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.FileFilter;

import aplicacion.*;
import sun.java2d.pipe.OutlineTextRenderer;


public class BodyTicGUI extends JFrame{
    
    private Salon salon = null;
    
    private JPanel botones;
    private JScrollPane contenedor;
    private JButton botonEntrada;
    private JButton botonSalida;
    private JButton botonInicio;
    private JButton botonParada;    
    private JButton botonDecision;     
    private FotoSalon foto;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem inicio;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem importar;
    private JMenuItem exportar;
    private JMenuItem salir;
    
    public BodyTicGUI() {
        super("Body Tic");
        try {
            salon=Salon.demeSalon();     
            elementos();
            acciones();
            prepareElementosMenu();
            prepareAccionesMenu();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void elementos() throws Exception {
        
        setLayout(new BorderLayout());    
        contenedor = new JScrollPane();
        
        foto= new FotoSalon();
        contenedor.getViewport().add(foto);
        
        botones=new JPanel(new GridLayout(1,2));
        botonEntrada=new JButton("Entren");
        botonInicio=new JButton("Inicien");
        botonParada=new JButton("Paren");
        botonDecision=new JButton("Decidan");          
        botonSalida=new JButton("Salgan");
        
        botones.add(botonEntrada);
        botones.add(botonInicio);
        botones.add(botonParada);
        botones.add(botonDecision);        
        botones.add(botonSalida);  
        
        add(contenedor,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
        
        pack();
        setSize(Salon.MAXIMO+100,Salon.MAXIMO+135);

        setResizable(false);
    }
    
    private void prepareElementosMenu() {
    	menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        menu = new JMenu("Menu");
        inicio = new JMenuItem("Inicio");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        importar = new JMenuItem("Importar");
        exportar = new JMenuItem("Exportar");
        salir = new JMenuItem("Salir");
        
        menu.add(inicio);
        menu.add(abrir);
        menu.add(guardar);
        menu.add(importar);
        menu.add(exportar);
        menu.add(salir);
        
        menuBar.add(menu);
    }
    
    private void prepareAccionesMenu() {
    	inicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionIniciar();
			}
		});
    	
    	abrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionAbrir();
			}
		});
    	
    	guardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionSalvar();
			}
		});
    	
    	importar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Atendiendo opci�n "+importar.getText(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		});
    	
    	exportar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Atendiendo opci�n "+exportar.getText(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		});
    	
    	salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Atendiendo opci�n "+salir.getText(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				opcionSalir();
			}
		});
    }
    
    private void opcionIniciar() {
    	salon = Salon.demeSalon();
    	actualice();
    }
    
    private void opcionAbrir(){
    	try{
    		JFileChooser chooser = new JFileChooser();
    		chooser.setCurrentDirectory(new File("/home/me/Documents"));
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("file dat","dat");
    		chooser.setFileFilter(filter);
    		int res = chooser.showOpenDialog(rootPane);
    		salon = Salon.demeSalon();
    		if (res != JFileChooser.CANCEL_OPTION) {
    			File file = new File(chooser.getSelectedFile().getAbsolutePath());
    			if (chooser.getSelectedFile().getName().toString().substring(chooser.getSelectedFile().getName().toString().length()-4) != ".dat")
    				JOptionPane.showMessageDialog(rootPane, "Tipo de archivo incorrecto", "ERROR", JOptionPane.WARNING_MESSAGE);	    		
	    		else {
	    			FileInputStream fis = new FileInputStream(file);
	    			ObjectInputStream obj = new ObjectInputStream(fis);
	    			String nombre = (String)obj.readObject();
		    		while (!nombre.equals("FIN")) {
		    			int posx = (int)obj.readObject();
		    			int posy = (int)obj.readObject();
		    			Color color = (Color)obj.readObject();
		    			if(color.equals(Color.BLACK))
		    				salon.adicione(new Deportista(salon, nombre, posx, posy));
		    			else if (color.equals(Color.ORANGE))
		    				salon.adicione(new DeportistaAvanzado(salon, nombre, posx, posy));
		    			else if (color.equals(Color.BLUE))
		    				salon.adicione(new DeportistaPerezoso(salon, nombre, posx, posy));
		    			else if (color.equals(Color.RED))
		    				salon.adicione(new DeportistaHablador(salon, nombre, posx, posy));
		    			else if (color.equals(Color.GRAY))
		    				salon.adicione(new Bola(salon, nombre, posx, posy));
		    			else if (color.equals(Color.YELLOW))
		    				salon.adicione(new Snitch(salon, nombre, posx, posy));
		    			nombre = (String)obj.readObject();
		    		}
		    		obj.close();
	    		}
    			
    		}
    	}catch(Throwable e){
    		e.printStackTrace();
    	}finally {
    		actualice();
		}
    }
    
    private void opcionSalvar() {
    	try {
    		JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("/home/me/Documents"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("file dat","dat");
    		chooser.setFileFilter(filter);
			int res = chooser.showSaveDialog(rootPane);
			File file = new File("");
			if (res != JFileChooser.CANCEL_OPTION){
				if (chooser.getSelectedFile().getName().toString().substring(chooser.getSelectedFile().getName().toString().length()-4) != ".dat"){
					file = new File(chooser.getSelectedFile().getAbsolutePath()+".dat");
				}else{
					file = new File(chooser.getSelectedFile().getAbsolutePath());
				}
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				int i = 0;
				while (i < salon.numeroEnSalon()) {
					System.out.println(i);
					EnSalon objeto = salon.deme(i);
					if (objeto != null) {
						oos.writeObject(objeto.getNombre());
						oos.writeObject(objeto.getPosicionX());
						oos.writeObject(objeto.getPosicionY());
						oos.writeObject(objeto.getColor());
					}					
					i++;
				}
				oos.writeObject("FIN");
				fos.close();
				oos.close();
			}						
		}catch (IOException e){
			e.printStackTrace();
		}
    }
    
    private void opcionSalir() {
    	System.exit(0);
    }    
    
    private void acciones(){
        
        ActionListener oyenteBotonEntrada=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                entrada();
            }   
        };  
        botonEntrada.addActionListener(oyenteBotonEntrada);
        
        ActionListener oyenteBotonInicio=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                inicio();
            }   
        };  
        botonInicio.addActionListener(oyenteBotonInicio);
        
        ActionListener oyenteBotonParada=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                parada();
            }   
        };  
        botonParada.addActionListener(oyenteBotonParada);
        
        ActionListener oyenteBotonDecision=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                decision();
            }   
        };  
        botonDecision.addActionListener(oyenteBotonDecision);
        
        ActionListener oyenteBotonSalida=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                salida();
            }   
        };  
        botonSalida.addActionListener(oyenteBotonSalida);
        
        WindowListener w = new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
                salir();
            }
        };  
        this.addWindowListener(w);
    }
    
    private void entrada(){
         salon.entrada();
         actualice();
    }
    
    private void salida(){
         salon.salida();
         actualice();
    }
    
    private void inicio(){
         salon.inicio();
         actualice();
    }
        
    
    private void parada(){       
        salon.parada();
        actualice();
    }       
  
    private void decision(){       
        salon.decision();
        actualice();
    }  
    
    private void actualice(){
        foto.actualice();
    }
    
    
    
    private void salir(){
        dispose();
        System.exit(0);
    }   
    
    
    
    public static void main(String[] args) {
        BodyTicGUI gui=new BodyTicGUI();
        gui.setVisible(true);
    }   
    
    private class FotoSalon extends JComponent {
        private int x,y;
        
        private static final int MAX=Salon.MAXIMO;
        
        
        public void actualice(){
            salon=Salon.demeSalon();
            repaint();
        }
        
        public void paintComponent(Graphics g){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 8)); 
            
            for (int i=1; i<=salon.numeroEnSalon(); i++) {
                
                EnSalon e=salon.deme(i);
                int x=e.getPosicionX();
                int y=MAX-e.getPosicionY();  
                
                g.setColor(e.getColor()); 
                g.drawString(e.mensaje(),x+20,y+10);   
                
                if (! (e instanceof Persona)){
                    g.fillOval(x+10,y+0,10,10);
                } else {
                    humano(g,(Persona)e,x,y);
                }
            }
            super.paintComponent(g);
        }
        
        
        public void humano(Graphics g, Persona e,int x, int y){
            int pos;
            g.setColor(Color.PINK);
            g.fillOval(x+10,y+0,10,10);/*cabeza*/
            g.setColor(e.getColor()); 
            g.drawLine(x+10+5,y+10,x+10+5,y+10+20);
            
            pos=e.getPosicionBrazo('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10);/*brazo izq arriba*/
            } else if (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+5);/*brazo izq al frente*/
            } else {
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+10);/*brazo izq abajo*/
            }
            
            pos=e.getPosicionBrazo('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+5,y+10);/*brazo der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+5,y+10+5);/*brazo der al frente*/
            } else{
                g.drawLine(x+10+5,y+10+5,x+5,y+10+10);/*brazo der abajo*/
            }
            
            g.drawLine(x+10+5,(y+15)+10+5,x+10+15,(y+15)+10+15);
            g.drawLine(x+10+5,(y+15)+10+5,x+5,(y+15)+10+15);
            
           pos=e.getPosicionPierna('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+5,(y+15)+10+15,x+5+10,(y+15)+10+15);/*pierna der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+5,(y+15)+10+15,x+5-10,(y+15)+10+15+5);/*pierna der al frente*/
            } else{
                g.drawLine(x+5,(y+15)+10+15,x+5,(y+15)+10+15+10);/*pierna der abajo*/
            }
            
            pos=e.getPosicionPierna('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15-10,(y+15)+10+15);/*pierna izq arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15+10,(y+15)+10+15+5);/*pierna izq al frente*/
            }else {
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15,(y+15)+10+15+10);/*piernaizqabajo*/
            }
        }
    }
}





