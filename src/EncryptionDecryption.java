public class EncryptionDecryption {
    public static String encrypt(String message, String key) {
        StringBuilder encryptedText = new StringBuilder();
        message = message.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                int shift = key.charAt(j % key.length()) - 'A';
                char encryptedChar = (char) ((c - 'A' + shift) % 26 + 'A');
                encryptedText.append(encryptedChar);
                j++;
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        encryptedText = encryptedText.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            if (Character.isLetter(c)) {
                int shift = key.charAt(j % key.length()) - 'A';
                char decryptedChar = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                decryptedMessage.append(decryptedChar);
                j++;
            } else {
                decryptedMessage.append(c);
            }
        }

        return decryptedMessage.toString();
    }
}
