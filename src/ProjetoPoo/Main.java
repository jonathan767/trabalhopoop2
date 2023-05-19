package ProjetoPoo;

import java.text.ParseException;

public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        } catch (ParseException e) {
            System.out.println("Ocorreu um erro ao inicializar a tela inicial.");
            e.printStackTrace();
        }
    }
}