package controller;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class RedesController {
	public RedesController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void ip() {
		String[] comando = new String[1];
		
		if (os().contains("Windows")) { // Para Windows:
			try {
				comando[0] = "IPCONFIG";
				Process p = Runtime.getRuntime().exec(comando);
				
				InputStream stream = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(stream);
				BufferedReader ler = new BufferedReader(leitor);
				String linha = ler.readLine();
				String adaptador = "";
				
				while (linha != null) {
					
					if (linha.contains("Adaptador")) { // Para armazenar o nome do adaptador
						adaptador = linha;
					}
					if (linha.contains("IPv4")) {
						String[] ipv4 = linha.split(":");
						ipv4[1] = (ipv4[1].trim());
						
						System.out.println(adaptador + " IPv4 - " + ipv4[1]);
					}
					linha = ler.readLine();
				}
				ler.close();
				leitor.close();
				stream.close();
				
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		else { // Para Linux:
			comando[0] = "ifconfig";
			
			try {
				Process p = Runtime.getRuntime().exec(comando);
				
				InputStream stream = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(stream);
				BufferedReader ler = new BufferedReader(leitor);
				String linha = ler.readLine();
				String[] adaptador = new String[2];
				
				while (linha != null) {
					if (linha.contains("flags")) {
						adaptador = linha.split(" ");
					}
					if (linha.contains("inet ")) {
						String[] ipv4 = linha.split("netmask");
						ipv4 = ipv4[0].split("inet");
						
						System.out.println("Adaptador " + adaptador[0] + " inet - " + ipv4[1].trim());
					}
					linha = ler.readLine();
				}
				
				ler.close();
				leitor.close();
				stream.close();
				
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void ping() {
		if (os().contains("Windows")) { // Para Windows:
			try {
				String[] comando = "ping -4 -n 10 www.google.com.br".split(" ");
				
				Process p = Runtime.getRuntime().exec(comando);
				InputStream stream = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(stream);
				BufferedReader ler = new BufferedReader(leitor);
				String linha = ler.readLine();
				
				while (linha != null) {
					if (linha.contains("milissegundos")) { // Uma linha antes das informações de tempo
						linha = ler.readLine();
						String[] media = linha.split("=");
						System.out.println("Tempo medio do ping:" + media[3]);
					}
					linha = ler.readLine();
				}
				ler.close();
				leitor.close();
				stream.close();
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		else { // Para Linux:
			String[] comando = "ping -4 -c 10 www.google.com.br".split(" ");
			
			try {
				Process p = Runtime.getRuntime().exec(comando);
				
				InputStream stream = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(stream);
				BufferedReader ler = new BufferedReader(leitor);
				String linha = ler.readLine();
				
				while (linha != null) {
					if (linha.contains("avg")) {
						String[] media = linha.split("=");
						media = media[1].split("/");
						
						System.out.println("Tempo medio do ping: " + media[1] + " ms");
					}
					linha = ler.readLine();
				}
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
