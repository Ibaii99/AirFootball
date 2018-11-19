package objetos;

import javax.swing.ImageIcon;

public class ObjetoCombobox {
	private final int value;
    private final String label;
    private final ImageIcon icono;

    public ObjetoCombobox(int value, String label, ImageIcon icono) {
        this.value = value;
        this.label = label;
        this.icono = icono;
    }
 
    public int getValue() {
        return this.value;
    }

    public String toString() {
        return this.label;
    }

	public String getLabel() {
		return label;
	}

	public ImageIcon getIcono() {
		return icono;
	}
    
}
