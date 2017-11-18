package gui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;

import logika.PoljanaZaIgru;

public class Igrica extends JFrame {

	public static void main(String[] args) {
		Igrica igrica = new Igrica(new PoljanaZaIgru(PoljanaZaIgru.LEVEL_BEGINER));
	}

	public Igrica(PoljanaZaIgru pzi) throws HeadlessException {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.getContentPane().add(new JButton("Hello World!"));
		this.setContentPane(new Mreza(pzi, "classic"));
		this.setVisible(true);
		this.setSize(this.getMaximumSize());
//		this.pack();
	}

}
