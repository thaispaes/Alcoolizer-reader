package main;

import VIEW.TelaLogin;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) throws ParseException, UnsupportedLookAndFeelException {
        startSystem();
    }

    private static void startSystem() {
        try {
            UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
            UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
        } catch (UnsupportedLookAndFeelException | ParseException ex) {
        }
        // new TelaLogin().setVisible(true);
//TESTA O LOADER AE TIRA O COMENTARIO AE E DEIXA O DE CIMA COMENTADO c:
        new TelaLogin().setVisible(true);
    }

}
