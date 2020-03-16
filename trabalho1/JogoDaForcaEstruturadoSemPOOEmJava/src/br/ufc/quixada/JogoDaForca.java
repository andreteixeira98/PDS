package br.ufc.quixada;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class JogoDaForca {
	//TO DO
	public static ArrayList<String> obterPalavras() throws IOException {//pegar palavras de um arquivo.
		File file = new File("src/files/timesDeFutebol.txt");
		FileReader fr = new FileReader(file);
		ArrayList<String> palavras = new ArrayList<String>();
		BufferedReader br = new BufferedReader(fr);
		String palavra;
		do {
			palavra = br.readLine();
			if(palavra != null) {
				palavras.add(palavra);
			}
		}while(palavra != null);
		br.close();
		fr.close();
		return palavras;
	}
	public static char[] initResposta(int tam) {
		char resposta[] = new char[tam];
		for(int i = 0;i<tam;i++) {
			resposta[i] = '-';
		}
		return resposta;
	}
	public static boolean[] criarBoneco() {
		boolean boneco[] = {false,false,false,false,false,false};
		return boneco;
	}
	public static String sortearPalavra(ArrayList<String> palavras) {
		Random sortearIndice = new Random();
		return palavras.get(sortearIndice.nextInt(10));
	}
	//TO DO
	public static void desenharCenario(boolean boneco[]) {
		if(boneco[0]) System.out.println("\t 0");
		if(boneco[1]) System.out.print("\t/");
		if(boneco[2]) System.out.print("|");
		if(boneco[3]) System.out.println("\\");
		if(boneco[4]) System.out.print("\t/ ");
		if(boneco[5]) System.out.println("\\");
	}
	public static boolean completouBoneco(boolean boneco[]) {
		for(int i = 0; i < boneco.length; i++) {
			if(!boneco[i]){
				return false;
			}
		}
		return true;
	}
	public static boolean acertouPalavra(String palavraSorteada,char resposta[]) {
		for(int i = 0; i < palavraSorteada.length(); i++) {
			if(palavraSorteada.charAt(i)!=' ' && 
					palavraSorteada.charAt(i)!=resposta[i]) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		Scanner lerDoTeclado = new Scanner(System.in);
		boolean boneco[] = criarBoneco();
		//TO DO
		ArrayList<String> palavras = obterPalavras();
		String palavraSorteada = sortearPalavra(palavras);
		char resposta[] = initResposta(palavraSorteada.length());
		boolean alterouResposta = false;
		int contErros = 0;
		
		char letra;
		while(true) {
			desenharCenario(boneco);
			System.out.println();
			System.out.println(resposta);
			System.out.print("Digite uma letra:");
			letra = lerDoTeclado.nextLine().charAt(0);//warning
			alterouResposta = false;
			
			for(int i = 0; i < palavraSorteada.length(); i++) {
				if(palavraSorteada.charAt(i) == letra) {
					resposta[i] = letra;
					alterouResposta = true;
				}
			}
			if(!alterouResposta) {
				System.out.println("errou!!");
				boneco[contErros++] = true;
			}
			//TO DO
			if(completouBoneco(boneco)) {
				desenharCenario(boneco);
				System.out.println(resposta);
				System.out.println("GAME OVER!");
				//reiniciarJogo();
				break;	
			}
			if(acertouPalavra(palavraSorteada, resposta)) {
				desenharCenario(boneco);
				System.out.println(resposta);
				System.out.println("VOCE VENCEU!");
				//reiniciarJogo();
				break;
			}
			
		}
		lerDoTeclado.close();

	}

}
