import app.lyricsapp.LyricsCLI;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LyricsCLITest {

    @Test
    void testRunCLI() throws IOException {
        // Rediriger System.in
        String input = "1\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Rediriger System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Exécuter la méthode
        LyricsCLI.runCLI();

        // Vérifier la sortie
        String expectedOutput = "\nBienvenue sur LyricsCLI !\n\n" +
                "\nMenu principal :\n" +
                "\n1- Rechercher une chanson" +
                "\n2- Accéder aux favoris" +
                "\n3- Quitter" +
                "\nSaisir votre choix : \n" +
                "\n\nMenu principal :\n" +
                "\n1- Rechercher une chanson" +
                "\n2- Accéder aux favoris" +
                "\n3- Quitter" +
                "\nSaisir votre choix : \n" +
                "\n\nFin du programme\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
