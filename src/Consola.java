import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

public class Consola {

	//Objetos de la interfaz
	private JFrame frame;
	private JPanel panel;
	private JTextArea textArea;
	private JTextField inputArea;
	private JScrollPane scroll;
	private JButton send;
	
	//indica a la consola si debe terminar la ejecucion del programa al cerrar la interfaz.
	private boolean closeOnClose = true;
	//Semaforo flujo de datos de entrada
	private boolean isListening = false;
	
	private boolean isInit = false;
	private final int height = 400;
	private final int width = 700;
	
	public Consola(boolean closeOnClose) {
		this.closeOnClose = closeOnClose;
		init();
	}
	//Inicializador para la interfaz
	private void init() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		textArea =  new JTextArea ( 16, 58 );
		inputArea = new JTextField(59);
		send = new JButton();
		
		frame.setBounds(0,0,width ,height);
		frame.setTitle("Console");
		//Centrar frame en pantalla
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		 
		panel = new JPanel();
	    panel.setBorder ( new TitledBorder ( new EtchedBorder (), "Console" ) );
	    
	    //Este evento se incia al abrir la ventana
	    frame.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        inputArea.requestFocus();
		    }
		});
	    
	    //Configuracion del area para salida de datos
	    textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
	    textArea.setEditable ( false ); 
	    //AutoScroll
	    DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    
	    //Scroll para el area de salida de datos
	    scroll = new JScrollPane ( textArea );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    
	    //Configuracion para la entrada de datos
	    //inputArea.setForeground(Color.WHITE);
	    //inputArea.setBackground(Color.BLACK);
	    inputArea.setEditable (true);
	    inputArea.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
	            	if(!isListening)
	            		return;
	            	isListening = false;
                }
	        }
	    });

	    //Configuracion boton
	    send.setText("Send");
	    send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isListening)
            		return;
            	isListening = false;
			}
	    	
	    });
	     
	    panel.add (scroll);
	    panel.add(inputArea);
	    panel.add(send);
	    frame.add (panel );
	    //frame.pack ();
	    frame.setResizable(false);
	    //frame.setLocationRelativeTo ( null );
	    if(closeOnClose)
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible ( true );
	    
	    isInit = true;
	    write("Console ready to work..." + "\n");
	}
	public <T> void writeLine(T text) {
		if(!isInit) return;
		textArea.append("\n" + String.valueOf(text));
	}
	public <T> void write(T text) {
		if(!isInit) return;
		textArea.append(String.valueOf(text));
	}
	
	/***Array in lines***/
	public <T> void writeArrayInLines(T[] array) {
		if(!isInit) return;
		for(T entry: array) {
			textArea.append("\n" + String.valueOf(entry) );
		}
	}
	public void writeArrayInLines(int[] array) {
		if(!isInit) return;
		for(int entry: array) {
			textArea.append("\n" + String.valueOf(entry) );
		}
	}
	public void writeArrayInLines(long[] array) {
		if(!isInit) return;
		for(long entry: array) {
			textArea.append("\n" + String.valueOf(entry) );
		}
	}
	public void writeArrayInLines(double[] array) {
		if(!isInit) return;
		for(double entry: array) {
			textArea.append("\n" + String.valueOf(entry) );
		}
	}
	public void writeArrayInLines(float[] array) {
		if(!isInit) return;
		for(float entry: array) {
			textArea.append("\n" + String.valueOf(entry) );
		}
	}
	public void writeArrayInLines(boolean[] array) {
		if(!isInit) return;
		for(boolean entry: array) {
			textArea.append("\n" + String.valueOf(entry) );
		}
	}
	
	/***Array same line***/
	public void writeArray(int[] array) {
		if(!isInit) return;
		textArea.append("\n" + "{");
		for(int i = 0; i < array.length; i++) {		
			textArea.append(String.valueOf(array[i]));
			if(i != array.length -1)
				textArea.append(",");
		}
		textArea.append("}");
	}
	public void writeArray(long[] array) {
		if(!isInit) return;
		textArea.append("\n" + "{");
		for(int i = 0; i < array.length; i++) {		
			textArea.append(String.valueOf(array[i]));
			if(i != array.length -1)
				textArea.append(",");
		}
		textArea.append("}");
	}
	public void writeArray(float[] array) {
		if(!isInit) return;
		textArea.append("\n" + "{");
		for(int i = 0; i < array.length; i++) {		
			textArea.append(String.valueOf(array[i]));
			if(i != array.length -1)
				textArea.append(",");
		}
		textArea.append("}");
	}
	public void writeArray(double[] array) {
		if(!isInit) return;
		textArea.append("\n" + "{");
		for(int i = 0; i < array.length; i++) {		
			textArea.append(String.valueOf(array[i]));
			if(i != array.length -1)
				textArea.append(",");
		}
		textArea.append("}");
	}
	public void writeArray(boolean[] array) {
		if(!isInit) return;
		textArea.append("\n" + "{");
		for(int i = 0; i < array.length; i++) {		
			textArea.append(String.valueOf(array[i]));
			if(i != array.length -1)
				textArea.append(",");
		}
		textArea.append("}");
	}
	public <T> void writeArray(T[] array) {
		if(!isInit) return;
		textArea.append("\n" + "{");
		for(int i = 0; i < array.length; i++) {		
			textArea.append(String.valueOf(array[i]));
			if(i != array.length -1)
				textArea.append(",");
		}
		textArea.append("}");
	}
	
	/**
	 * Devuelve un String
	 * @return
	 */
	public String read() {
		if(!isInit) return "";
		String result ="";
		isListening = true;
		writeLine("Waiting for String input...");
		while(isListening) {
			result = inputArea.getText();
		}
		inputArea.setText("");
		writeLine("Input: " + result);
		return result;
	}
	
	/**
	 * Devuelve un Integer y en caso de que los datos de entrada no sean digito el resultado sera 0
	 * @return
	 */
	public int readInt() {
		if(!isInit) return 0;
		int result = 0;
		isListening = true;
		writeLine("Waiting for Integer input...");
		while(isListening) {
			try {
				result = Integer.parseInt(inputArea.getText());
			} catch(NumberFormatException nfe) {
				result = 0;
			}
		}
		inputArea.setText("");
		writeLine("Input: " + result);
		return result;
	}
	
	/**
	 * Devuelve un Double y en caso de que los datos de entrada no sean digito el resultado sera 0
	 * @return
	 */
	public double readDouble() {
		if(!isInit) return 0D;
		double result = 0D;
		isListening = true;
		writeLine("Waiting for Double input...");
		while(isListening) {
			try {
				result = Double.parseDouble(inputArea.getText());
			} catch(NumberFormatException nfe) {
				result= 0D;
			}
		}
		inputArea.setText("");
		writeLine("Input: " + result);
		return result;
	}
	
	/**
	 * Devuelve un Float y en caso de que los datos de entrada no sean digito el resultado sera 0
	 * @return
	 */
	public float readFloat() {
		if(!isInit) return 0F;
		float result = 0F;
		isListening = true;
		writeLine("Waiting for Float input...");
		while(isListening) {
			try {
				result = Float.parseFloat(inputArea.getText());
			} catch(NumberFormatException nfe) {
				result= 0F;
			}
		}
		inputArea.setText("");
		writeLine("Input: " + result);
		return result;
	}
	
	/**
	 * Devuelve un Long y en caso de que los datos de entrada no sean digito el resultado sera 0
	 * @return long
	 */
	public long readLong() {
		if(!isInit) return 0L;
		long result = 0L;
		isListening = true;
		writeLine("Waiting for Long input...");
		while(isListening) {
			try {
				result = Long.parseLong(inputArea.getText());
			} catch(NumberFormatException nfe) {
				result= 0L;
			}
		}
		inputArea.setText("");
		writeLine("Input: " + result);
		return result;
	}
	/**
	 * Devuelve un Boolean y en caso de que los datos de entrada no sean boolean el resultado sera false
	 * @return boolean
	 */
	public boolean readBoolean() {
		if(!isInit) return false;
		boolean result = false;
		isListening = true;
		writeLine("Waiting for Boolean input...");
		while(isListening) {
			try {
				result = Boolean.parseBoolean(inputArea.getText());
			} catch(NumberFormatException nfe) {
				result= false;
			}
		}
		inputArea.setText("");
		writeLine("Input: " + result);
		return result;
	}
	public String showOptionPanel(String title, String description,String[] options) {
		String result = "undefined";
		result = (String)JOptionPane.showInputDialog(null,description,title,JOptionPane.DEFAULT_OPTION,null, options,options[0]);
		return result;
	}
	public void close() {
		frame.dispose();
	}
	//TEST

	
	
	
/*
	public static void main(String[] args) {
		
		Consola consola = new Consola(true);
		int[] array = {1,2,3,4};
		consola.writeArray(array);
		
	}
*/
}