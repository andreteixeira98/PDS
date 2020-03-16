package br.ufc.quixada;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class JogoDaForca {
	public static ArrayList<String> obterPalavras() throws IOException {
		File file = new File("src/files/timesDeFutebol.txt");
		FileReader fr = new FileReader(file);
		ArrayList<String> palavras = new ArrayList<String>();
		BufferedReader br = new BufferedReader(fr);
		String palavra;
		do {
			palavra = br.readLine();
			if(palavra != null) palavras.add(palavra);
		}while(palavra != null);
		br.close();
		fr.close();
		return palavras;
	}
	public static char[] initResposta(int tam) {
		char resposta[] = new char[tam];
		for(int i = 0;i < tam; i++) resposta[i] = '-';
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
	public static void desenharCenario(boolean boneco[]) {
		System.out.println(" __________");
		System.out.println("|          |");
		if(boneco[0]) System.out.println("|          O");
		if(boneco[1]) {
			System.out.print("|         /");
			if(!boneco[2])
				System.out.println();
		}
		if(boneco[2]) {
			System.out.print("|");
			if(!boneco[3])
				System.out.println();
		}
		if(boneco[3]) {
			System.out.println("\\");
		}
		if(boneco[4]) {
			System.out.print("|         / ");
			if(!boneco[5])
				System.out.println();
		}
		if(boneco[5]) System.out.println("\\");
		System.out.println("|");
		System.out.println("|");
	}
	public static boolean completouBoneco(boolean boneco[]) {
		for(int i = 0; i < boneco.length; i++)
			if(!boneco[i]) return false;
		return true;
	}
	public static boolean acertouPalavra(String palavraSorteada,char resposta[]) {
		for(int i = 0; i < palavraSorteada.length(); i++) {
			if(palavraSorteada.charAt(i)!=' ' && palavraSorteada.charAt(i)!=resposta[i])
				return false;
		}return true;
	}
	public static void main(String[] args) throws IOException {
		Scanner lerDoTeclado = new Scanner(System.in);
		boolean boneco[] = criarBoneco();
		ArrayList<String> palavras = obterPalavras();
		String palavraSorteada = sortearPalavra(palavras);
		char resposta[] = initResposta(palavraSorteada.length());
		ArrayList<Character> letrasChutadas = new ArrayList<Character>();
		char letraChutada;
		boolean alterouResposta = false;
		boolean letraJaChutada = false;
		int contErros = 0;
		boolean iniciarJogo = false;
		
		System.out.println("Dica: um time de futebol com "+ palavraSorteada.length() + " letras");
		while(true) {
			desenharCenario(boneco);
			System.out.println();
			System.out.println(resposta);
			System.out.println("letras Chutadas:"+letrasChutadas.toString());
			System.out.print("Digite uma letra:");
			letraChutada = lerDoTeclado.nextLine().charAt(0);//warning
			alterouResposta = false;
			letraJaChutada = false;
			if(!letrasChutadas.contains(letraChutada)) {
				letrasChutadas.add(letraChutada);
			}else letraJaChutada = true;
			for(int i = 0; i < palavraSorteada.length(); i++) {
				if(palavraSorteada.charAt(i) == letraChutada) {
					resposta[i] = letraChutada;
					alterouResposta = true;
				}
			}if(!alterouResposta && !letraJaChutada) {
				boneco[contErros++] = true;
			}
			if(completouBoneco(boneco)) {
				desenharCenario(boneco);
				System.out.println();
				System.out.println(resposta);
				System.out.println("GAME OVER!");
				//reiniciarJogo();
				break;	
			}if(acertouPalavra(palavraSorteada, resposta)) {
				desenharCenario(boneco);
				System.out.println();
				System.out.println(resposta);
				System.out.println("VOCE VENCEU!");
				//reiniciarJogo();
				break;
			}	
		}lerDoTeclado.close();
	}
}
