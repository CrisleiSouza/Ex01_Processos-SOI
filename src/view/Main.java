package view;
import controller.RedesController;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		String[] opcoes = {"IP", "Ping", "Finalizar"};
		int escolha = 0;
		
		while (escolha != 2) {
			escolha = JOptionPane.showOptionDialog(null, "Selecione uma das opções.\nO método 'Ping' pode levar alguns instantes",
					"Escolha a Próxima Ação:", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
			if (escolha == 0 || escolha == 1) {
				RedesController redesCont = new RedesController();
				if (escolha == 0) {
					redesCont.ip();
				}
				else {
					redesCont.ping();
				}
			}
			else {
				System.out.println("Sistema finalizado."); break;
			}
			
			/*switch(escolha) {  
				case 0: redesCont.ip(); break;
				case 1: redesCont.ping(); break;
				case 2: System.out.println("Sistema finalizado."); break;
			}*/
		}	
	}
}