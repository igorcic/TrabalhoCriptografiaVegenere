import java.util.ArrayList;
import java.util.List;

public class FindKey {
    public static String findKey(String encryptedText, int keyLength, double[] languageFrequencies) {
        encryptedText = encryptedText.toUpperCase();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < keyLength; i++) {
            int bestShift = 0;
            double minScore = Double.MAX_VALUE;

            for (int shift = 0; shift < 26; shift++) {
                StringBuilder substring = new StringBuilder();
                for (int j = i; j < encryptedText.length(); j += keyLength) {
                    char c = encryptedText.charAt(j);
                    if (Character.isLetter(c)) {
                        int decryptedChar = (c - 'A' - shift + 26) % 26;
                        substring.append((char) (decryptedChar + 'A'));
                    }
                }

                double score = calculateFrequencyScore(substring.toString(), languageFrequencies);
                if (score < minScore) {
                    minScore = score;
                    bestShift = shift;
                }
            }

            char keyChar = (char) ('A' + bestShift);
            key.append(keyChar);
        }

        return key.toString();
    }


    public static int findKeySize(String encryptedText) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 0; i < encryptedText.length() - 2; i++) {
            String seq = encryptedText.substring(i, i + 3);

            for (int j = i + 3; j < encryptedText.length() - 2; j++) {
                if (encryptedText.substring(j, j + 3).equals(seq)) {
                    int dist = j - i;


                    for (int k = 2; k <= 20; k++) {
                        if (dist % k == 0) {
                            factors.add(k);
                            break;
                        }
                    }
                    break;
                }
            }
        }


        int maxSize = 0; // Variável para rastrear o tamanho da chave com mais ocorrências
        int maxCount = 0; // Variável para rastrear o número máximo de ocorrências

        for (int i = 5; i <= 10; i++) { // Restrição de tamanho mínimo e máximo
            int count = countOccurrences(factors, i);


            // Atualize o tamanho máximo se encontrar um novo máximo
            if (count > maxCount) {
                maxCount = count;
                maxSize = i;
            }
        }

        // Verifique se o tamanho máximo encontrado tem o número mínimo de ocorrências (1) antes de retorná-lo
        if (maxCount >= 1) {
            return maxSize;
        } else {
            // Se nenhum tamanho tiver o número mínimo de ocorrências, você pode retornar 0 ou realizar outra ação apropriada.
            return 0;
        }
    }

        public static int countOccurrences(List<Integer> list, int target) {
            int count = 0;
            for (int num : list) {
                if (num == target) {
                    count++;
                }
            }
            return count;
        }
    public static double calculateFrequencyScore(String text, double[] languageFrequencies) {
        text = text.toUpperCase();
        int[] letterCounts = new int[26];
        int totalLetters = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCounts[c - 'A']++;
                totalLetters++;
            }
        }

        double score = 0.0;
        for (int i = 0; i < 26; i++) {
            double expectedFrequency = languageFrequencies[i] * totalLetters;
            double observedFrequency = letterCounts[i];
            score += Math.pow(expectedFrequency - observedFrequency, 2) / expectedFrequency;
        }

        return score;
    }
}
