package Model;
/**
 * The value of each type represents a increment on the second byte of the number
 * */
public enum PlayerColor {
	VERMELHO(256), VERDE(512), AZUL(768), AMARELO(1024);

	private short color;
	
	PlayerColor(int value) {
        this.color = (short) value;
    }

    public short getValue() {
       return color;
    }
}
