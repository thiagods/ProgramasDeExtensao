import java.util.Scanner;

public class ConversorBinario
{	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		double numero;
		boolean negativo = false;

		System.out.print("Entre com o numero: ");
		numero = scan.nextDouble();
		
		if(numero<0)
		{
			numero = numero*-1;
			negativo = true;
		}

		int inteiro = (int) numero;
		double fracao = numero-inteiro;

		String numeroInteiro = conversorInteiro(inteiro);	
		String numeroFracao = conversorFracao(fracao, numeroInteiro);		
		String numeroBinario = numeroInteiro+"."+numeroFracao;		

		if(negativo == true)
		{
			numeroBinario = "-" + numeroBinario;
		}

		System.out.println("Convertido para Binario: "+numeroBinario);

		double decimal = desconversorBinario(numeroBinario);

		System.out.println("Desconvertido de Binario: "+decimal);
		scan.close();
	}

	public static String conversorInteiro(int numero)
	{		
		int resto;
		int num = numero;		
		String binario = "";
		
		while (num != 0)
		{	
			resto = num % 2;
			num = num/2;
			binario = binario + Integer.toString((int) resto);
		}

		StringBuffer resultado = new StringBuffer(binario);
		binario = resultado.reverse().toString();

		if(binario.equals(""))
		{
			binario = "0";
		}		
		return binario;
	}

	public static String conversorFracao(double numero, String inteiro)
	{		
		if(numero<0)
		{
			numero = numero*-1;
		}

		double num = numero;
		int k=0;
		String binario = "";

		while(num!=1.0 && k<23-inteiro.length()-1)
		{
			num = num*2;
			binario = binario + Integer.toString((int) num);

			if(num>1)
			{
				num = num-1;				
			}
			k++;
		}
		return binario;
	}

	public static double desconversorBinario(String numero)
	{
		boolean negativo = false;
		double inteiroFinal = 0;
		double fracaoFinal = 0;
		double numeroFinal = 0;

		if(numero.contains("-"))
		{
			negativo = true;
			numero = numero.substring(1, numero.length());
		}		

		String [] vet = numero.split("\\.");

		for (int i=0; i<vet[0].length(); i++)
		{  
			inteiroFinal = inteiroFinal +  
					Integer.parseInt(Character.toString(vet[0].charAt(i)),10)
					* Math.pow(2, (vet[0].length()-i-1));
		}

		for (int i=0; i<vet[1].length(); i++)
		{  
			fracaoFinal = fracaoFinal +  
					Integer.parseInt(Character.toString(vet[1].charAt(i)),10)
					* Math.pow(2, -(i+1));
		}

		numeroFinal = inteiroFinal + fracaoFinal;

		if(negativo == true)
		{
			numeroFinal = numeroFinal*(-1);
		}		
		return numeroFinal;		
	}
}