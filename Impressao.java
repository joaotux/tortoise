import java.io.FileOutputStream;
import java.io.PrintStream;

public class Impressao {
	public Impressao(String valor) {
		FileOutputStream fos = null;
		PrintStream ps = null;

		try {
			fos = new FileOutputStream("LPT1:");
		} catch (Exception e) {
			// erro
		}

		try {
			ps = new PrintStream(fos);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Esta linha imprime na porte LPT1 o valor que esta na variavel
		// "valor".
		ps.println(valor);

	}
}