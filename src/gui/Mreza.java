package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logika.PoljanaZaIgru;

public class Mreza extends JPanel {
	private PoljanaZaIgru pzi;
	private String type;

	public Mreza(PoljanaZaIgru pzi) {
		this(pzi, "text");
	}

	public Mreza(PoljanaZaIgru pzi, String type) {
		super();
		this.pzi = pzi;
		this.type = type;
		int visina = pzi.getVisina();
		int sirina = pzi.getSirina();
		this.setLayout(new GridLayout(visina, sirina));
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				MojeDugme md = new MojeDugme(i, j);
				if (!type.equals("text")) {
					try {
						md.setIcon(new ImageIcon(ImageIO.read(new File(type + "/blank.png"))));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}
				md.addMouseListener(new MojOsluskivac(i, j));
				this.add(md, i, j);
			}
		}
		
		
		
	}

	public void osvjeziTabelu() {
		for (int i = 0; i < pzi.getVisina(); i++) {
			for (int j = 0; j < pzi.getSirina(); j++) {
				String c = "" + pzi.getPoljana(i, j);
				if (!type.equals("text")) {
					try {
						((MojeDugme)(this.getComponent((pzi.getVisina() - i - 1) * pzi.getSirina() + j))).setIcon(new ImageIcon(ImageIO.read(new File(type + "/" + pripremi(c) + ".png"))));
						((MojeDugme)(this.getComponent((pzi.getVisina() - i - 1) * pzi.getSirina() + j))).setDisabledIcon(new ImageIcon(ImageIO.read(new File(type + "/" + pripremi(c) + ".png"))));
					} catch (IOException e) {
						System.out.println("GRESKA!!! ovdje je c = " + c);
						e.printStackTrace();
					}
				} else  {
					((MojeDugme)(this.getComponent((pzi.getVisina() - i - 1) * pzi.getSirina() + j))).setText(c);
				}
//				System.out.println("komponenta (" + i  +", " + j +") je stavljena na " + ((pzi.getVisina() - i - 1) * pzi.getSirina() + (pzi.getSirina() - j - 1)));
				if (!(c.equals(" ") || c.equals("P"))) {
					((MojeDugme)(this.getComponent((pzi.getVisina() - i - 1) * pzi.getSirina() +j))).setEnabled(false);
				}
			}
		}
	}

	private String pripremi(String c) {
		if (c.equals(" ")) {
			return "blank";
		}
		if (c.equals("P")) {
			return "flag";
		}
		if (c.equals("*")) {
			return "mine";
		}
		return c;
	}

	class MojeDugme extends JButton {
		int x;
		int y;

		public MojeDugme(int i, int j) {
			x = i;
			y = j;
		}
		
	}
	
	class MojOsluskivac implements MouseListener {
		int x;
		int y;

		public MojOsluskivac(int i, int j) {
			x = i;
			y = j;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Kliknuo si polje (" + x + ", " + y + ").");
			MojeDugme md = (MojeDugme)e.getComponent();
/*
			md.setEnabled(!md.isEnabled());
			md.setText("" + e.getButton());
*/
//			if (e.getButton() == MouseEvent.BUTTON1) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				if (SwingUtilities.isRightMouseButton(e)) {
					if (!md.isEnabled()) {
						pzi.odigraj(x, y, PoljanaZaIgru.POTEZ_OBA_KLIKA);
						osvjeziTabelu();
						System.out.println(pzi);
					}
				} else {
					if (md.isEnabled()) {
						if (pzi.getPoljana(x, y) == ' ') {
							pzi.odigraj(x, y, PoljanaZaIgru.POTEZ_LIJEVI_KLIK);
							osvjeziTabelu();
							System.out.println(pzi);
						}
					}
				}
			} else {
//				if (e.getButton() == MouseEvent.BUTTON3) {
				if (SwingUtilities.isRightMouseButton(e)) {
					if (md.isEnabled()) {
						pzi.odigraj(x, y, PoljanaZaIgru.POTEZ_DESNI_KLIK);
						osvjeziTabelu();
						System.out.println(pzi);
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
	}

}
