public class TextProcessor {
    public static String processText(String text) {
        // Remove espaços em branco e converte para maiúsculas
        String processedText = text.replaceAll("\\s", "").toUpperCase();

        // Remove números e caracteres especiais
        processedText = processedText.replaceAll("[^A-Z]", "");

        return processedText;
    }
}
