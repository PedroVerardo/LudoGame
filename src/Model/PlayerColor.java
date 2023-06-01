package Model;
/**
 * The value of each type represents a increment on the second byte of the number
 * */
enum PlayerColor {
	VERMELHO(1024), VERDE(256), AZUL(768), AMARELO(512);

	private short color;
	
	PlayerColor(int value) {
        this.color = (short) value;
    }

    short getValue() {
       return color;
    }
}
