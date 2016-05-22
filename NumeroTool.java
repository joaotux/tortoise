package br.com.essys.fw.ambos.tool;

import java.math.BigDecimal;

/**
 * @author JoaoTux
 * 
 *         Classe destinada a informar o extenso de determinado valor passado
 *         Combo parametro.
 *
 */
public class NumeroTool {

	/**
	 * função para retornar os valores em extenso, estando limitando a no m?ximo
	 * 3 casas decimais e o valor de 999 bilhões.
	 * 
	 * @param valor
	 *            Valor a ser escrito por extenso.
	 * @return Retorna o valor por extenso.
	 * @throws Exception
	 */
	public static String extenso(BigDecimal valor) throws Exception {
		final int LIMITE = 12;
		int casaDecimal = valor.scale();
		if (casaDecimal > 3)
			throw new Exception("No m?ximo 3 casas decimais");
		int ponto = valor.toString().indexOf(".");
		String valorInteiro = valor.toString();
		String valorCentavo = rpad("0", casaDecimal);
		if (valor.toString().indexOf(".") == -1)
			ponto = valor.toString().indexOf(",");
		if (ponto > 0) {
			valorInteiro = valor.toString().substring(0, ponto);
			valorCentavo = valor.toString().substring(ponto + 1);
		}
		valorInteiro = lpad(valorInteiro, LIMITE);
		if (casaDecimal == 1)
			valorCentavo = "0" + valorCentavo + "0";
		else if (casaDecimal == 2)
			valorCentavo = "0" + valorCentavo;
		else
			valorCentavo = rpad(valorCentavo, 3);
		String bloco[] = new String[5];
		int p = 0;
		for (int i = 0; i < 15; i += 3) {
			bloco[p++] = (valorInteiro + valorCentavo).substring(i, i + 3);
		}
		String[] etapa1 = { "zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
				"onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove" };
		String[] etapa2 = { "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta",
				"noventa" };
		String[] etapa3 = { "", "cem", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seissentos",
				"setescentos", "oitocentos", "novecentos" };
		String[] etapa4 = { "", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seissentos",
				"setescentos", "oitocentos", "novecentos" };

		StringBuffer valorExtenso = new StringBuffer("");
		for (int i = 0; i <= 4; i++) {
			if (!bloco[i].equals("000")) {
				if (!valorExtenso.toString().equals(""))
					valorExtenso.append(" e ");
				StringBuffer retorno = new StringBuffer("");
				int valorInt = Integer.parseInt(bloco[i]);
				if (valorInt % 100 == 0) {
					retorno.append(etapa3[valorInt / 100]);
				} else {
					retorno.append(etapa4[Integer.parseInt(bloco[i].substring(0, 1))]);
					if (!bloco[i].substring(0, 1).equals("0"))
						retorno.append(" e ");
					valorInt = Integer.parseInt(bloco[i].substring(1));
					if (valorInt >= 1 && valorInt <= 19) {
						retorno.append(etapa1[valorInt]);
					} else if (valorInt % 10 == 0) {
						int centavoPosicao = 0;
						centavoPosicao = valorInt / 10;
						retorno.append(etapa2[centavoPosicao]);
					} else {
						int parte1 = Integer.parseInt(bloco[i].substring(0, 1));
						int parte2 = Integer.parseInt(bloco[i].substring(1));
						retorno.append(etapa2[parte1]);
						retorno.append(" e ");
						retorno.append(etapa1[parte2]);
					}
				}
				if (i == 0)
					retorno.append((bloco[i].equals("001") ? " bilh?o" : " bilh?es"));
				else if (i == 1)
					retorno.append((bloco[i].equals("001") ? " milh?o" : " milh?es"));
				else if (i == 2)
					retorno.append(" mil");
				else if (i == 3)
					retorno.append((!valorInteiro.equals(lpad("1", LIMITE)) ? " reais" : " real"));
				else if (i == 4)
					retorno.append((bloco[i].equals("001") ? " centavo" : " centavos"));
				valorExtenso.append(retorno);
			}
		}
		return valorExtenso.toString();
	}

	public static String rpad(String valor, int tamanho) {
		StringBuffer acumula = new StringBuffer("");
		int corte = tamanho - valor.length();
		for (int i = 0; i < tamanho; i++)
			acumula.append("0");
		if (corte > 0)
			return (valor + acumula).substring(0, tamanho);
		else
			return valor;
	}

	public static String lpad(String valor, int tamanho) {
		StringBuffer acumula = new StringBuffer("");
		int corte = tamanho - valor.length();
		for (int i = 0; i < tamanho; i++)
			acumula.append("0");
		if (corte > 0)
			return acumula.substring(0, corte) + valor;
		else
			return valor;
	}
}
