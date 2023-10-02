import java.io.*;
import java.util.Scanner;

public class VigenereCipherMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text, key;
        int keyLength;
        int choice, choiceLanguage, choiceMessageType;
        double[] portugueseLetterFrequencies = LoadFrequenciesFile.loadFrequenciesFromFile("frequenciaLetras\\frequencia_portugues.txt");
        double[] englishLetterFrequencies = LoadFrequenciesFile.loadFrequenciesFromFile("frequenciaLetras\\frequencia_ingles.txt");
        String fileName;


        do {
            System.out.println("Escolha uma opção:");
            System.out.println("<1> Criptografar");
            System.out.println("<2> Descriptografar");
            System.out.println("<3> Descobrir a chave com um tamanho estimado");
            System.out.println("<4> Descobrir a chave");
            System.out.println("<5> Sair");
            System.out.print("Opção: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após a entrada de escolha

            switch (choice) {
                case 1:
                    System.out.println("<1> para criptografar o arquivo de texto");
                    System.out.println("<2> para criptografar uma mensagem enviada");
                    choiceMessageType = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após a entrada de escolha

                    if (choiceMessageType == 1) {
                        try {
                            System.out.println("Digite o nome do arquivo: ");
                            fileName = scanner.nextLine();
                            String filePath = "testesTextos\\" + fileName;
                            BufferedReader br = new BufferedReader(new FileReader(filePath));
                            StringBuilder content = new StringBuilder();
                            String line;

                            while ((line = br.readLine()) != null) {
                                content.append(line);
                                content.append("\n");
                            }

                            br.close();
                            text = TextProcessor.processText(content.toString());
                            System.out.print("Digite a chave: ");
                            key = scanner.nextLine();
                            String encryptedText = EncryptionDecryption.encrypt(text, key);

                            // Criar um arquivo de retorno com o nome original + "_ret"
                            String nomeArquivoRetorno = "retornoTextos\\" + fileName + "_RET";
                            PrintWriter writer = new PrintWriter(nomeArquivoRetorno, "UTF-8");
                            writer.print(encryptedText);
                            writer.close();
                            System.out.println("Mensagem cifrada: " + encryptedText);
                            System.out.println("Arquivo de retorno criado com sucesso: " + nomeArquivoRetorno);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (choiceMessageType == 2) {
                        System.out.print("Digite a mensagem que vai ser criptografada: ");
                        text = scanner.nextLine();
                        System.out.print("Digite a chave: ");
                        key = scanner.nextLine();
                        text = TextProcessor.processText(text);
                        System.out.println("Mensagem cifrada: " + EncryptionDecryption.encrypt(text, key));
                    }

                    break;

                case 2:
                    System.out.println("<1> para descriptografar o arquivo de texto");
                    System.out.println("<2> para descriptografar uma mensagem enviada");
                    choiceMessageType = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após a entrada de escolha

                    if (choiceMessageType == 1) {
                        try {
                            System.out.println("Digite o nome do arquivo: ");
                            fileName = scanner.nextLine();
                            String filePath = "testesTextos\\" + fileName;
                            BufferedReader br = new BufferedReader(new FileReader(filePath));
                            StringBuilder content = new StringBuilder();
                            String line;

                            while ((line = br.readLine()) != null) {
                                content.append(line);
                                content.append("\n");
                            }

                            br.close();
                            text = TextProcessor.processText(content.toString());
                            System.out.print("Digite a chave: ");
                            key = scanner.nextLine();
                            String decryptedText = EncryptionDecryption.decrypt(text, key);

                            // Criar um arquivo de retorno com o nome original + "_ret"
                            String nomeArquivoRetorno = "retornoTextos\\" + fileName + "_RET";;
                            PrintWriter writer = new PrintWriter(nomeArquivoRetorno, "UTF-8");
                            writer.print(decryptedText);
                            writer.close();
                            System.out.println("Mensagem decifrada: " + decryptedText);
                            System.out.println("Arquivo de retorno criado com sucesso: " + nomeArquivoRetorno);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (choiceMessageType == 2) {
                        System.out.print("Digite a mensagem que vai ser descriptografada: ");
                        text = scanner.nextLine();
                        System.out.print("Digite a chave: ");
                        key = scanner.nextLine();
                        text = TextProcessor.processText(text);
                        String decryptedMessage = EncryptionDecryption.decrypt(text, key);
                        System.out.println("Mensagem decifrada: " + decryptedMessage);
                    }

                    break;

                case 3:
                    System.out.print("<1> pt e <2> eng: ");
                    choiceLanguage = scanner.nextInt();
                    System.out.println("<1> para descobrir chave de um arquivo de texto");
                    System.out.println("<2> para descobrir chave de uma mensagem enviada");
                    choiceMessageType = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após a entrada de escolha

                    if (choiceMessageType == 1) {
                        try {
                            System.out.println("Digite o nome do arquivo: ");
                            fileName = scanner.nextLine();
                            String filePath = "testesTextos\\" + fileName;
                            BufferedReader br = new BufferedReader(new FileReader(filePath));
                            StringBuilder content = new StringBuilder();
                            String line;

                            while ((line = br.readLine()) != null) {
                                content.append(line);
                                content.append("\n");
                            }

                            br.close();
                            text = TextProcessor.processText(content.toString());
                            System.out.print("Digite o tamanho da chave: ");
                            keyLength = scanner.nextInt();
                            text = TextProcessor.processText(text);

                            if (choiceLanguage == 1) {
                                String foundKey = FindKey.findKey(text, keyLength, portugueseLetterFrequencies);
                                System.out.println("A chave é: " + foundKey);
                                String decryptedText = EncryptionDecryption.decrypt(text, foundKey);
                                System.out.println("A mensagem decifrada: " + decryptedText);

                                // Criar um arquivo de retorno na pasta "retornoTextos" com o nome original + "_ret"
                                String nomeArquivoRetorno = "retornoTextos\\" + fileName + "_RET";
                                PrintWriter writer = new PrintWriter(nomeArquivoRetorno, "UTF-8");
                                writer.print(decryptedText);
                                writer.close();
                                System.out.println("Arquivo de retorno criado com sucesso em: " + nomeArquivoRetorno);
                            } else if (choiceLanguage == 2) {
                                String foundKey = FindKey.findKey(text, keyLength, englishLetterFrequencies);
                                System.out.println("A chave é: " + foundKey);
                                String decryptedText = EncryptionDecryption.decrypt(text, foundKey);
                                System.out.println("A mensagem decifrada: " + decryptedText);

                                // Criar um arquivo de retorno na pasta "retornoTextos" com o nome original + "_ret"
                                String nomeArquivoRetorno = "retornoTextos\\" + fileName + "_RET";
                                PrintWriter writer = new PrintWriter(nomeArquivoRetorno, "UTF-8");
                                writer.print(decryptedText);
                                writer.close();
                                System.out.println("Arquivo de retorno criado com sucesso em: " + nomeArquivoRetorno);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (choiceMessageType == 2) {
                        System.out.print("Digite a mensagem que vai ser descriptografada: ");
                        text = scanner.nextLine();
                        System.out.print("Digite a chave: ");
                        key = scanner.nextLine();
                        text = TextProcessor.processText(text);
                        String decryptedMessage = EncryptionDecryption.decrypt(text, key);
                        System.out.println("Mensagem decifrada: " + decryptedMessage);
                    }

                    break;

                case 4:
                    System.out.print("<1> pt e <2> eng: ");
                    choiceLanguage = scanner.nextInt();
                    System.out.println("<1> para descobrir chave de um arquivo de texto");
                    System.out.println("<2> para descobrir chave de uma mensagem enviada");
                    choiceMessageType = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após a entrada de escolha

                    if (choiceMessageType == 1) {
                        try {
                            System.out.println("Digite o nome do arquivo: ");
                            fileName = scanner.nextLine();
                            String filePath = "testesTextos\\" + fileName;
                            BufferedReader br = new BufferedReader(new FileReader(filePath));
                            StringBuilder content = new StringBuilder();
                            String line;

                            while ((line = br.readLine()) != null) {
                                content.append(line);
                                content.append("\n");
                            }

                            br.close();
                            text = TextProcessor.processText(content.toString());

                            int keySize = FindKey.findKeySize(text);
                            System.out.println("O tamanho da chave é: " + keySize);

                            if (choiceLanguage == 1) {
                                String foundKey = FindKey.findKey(text, keySize, portugueseLetterFrequencies);
                                System.out.println("A chave é: " + foundKey);
                                String decryptedText = EncryptionDecryption.decrypt(text, foundKey);
                                System.out.println("A mensagem decifrada: " + decryptedText);

                                // Criar um arquivo de retorno na pasta "retornoTextos" com o nome original + "_ret"
                                String nomeArquivoRetorno = "retornoTextos\\" + fileName + "_RET";
                                PrintWriter writer = new PrintWriter(nomeArquivoRetorno, "UTF-8");
                                writer.print(decryptedText);
                                writer.close();
                                System.out.println("Arquivo de retorno criado com sucesso em: " + nomeArquivoRetorno);
                            } else if (choiceLanguage == 2) {
                                String foundKey = FindKey.findKey(text, keySize, englishLetterFrequencies);
                                System.out.println("A chave é: " + foundKey);
                                String decryptedText = EncryptionDecryption.decrypt(text, foundKey);
                                System.out.println("A mensagem decifrada: " + decryptedText);

                                // Criar um arquivo de retorno na pasta "retornoTextos" com o nome original + "_ret"
                                String nomeArquivoRetorno = "retornoTextos\\" + fileName + "_RET";
                                PrintWriter writer = new PrintWriter(nomeArquivoRetorno, "UTF-8");
                                writer.print(decryptedText);
                                writer.close();
                                System.out.println("Arquivo de retorno criado com sucesso em: " + nomeArquivoRetorno);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (choiceMessageType == 2) {
                        System.out.print("Digite o texto cifrado: ");
                        text = scanner.nextLine();
                        text = TextProcessor.processText(text);

                        if (choiceLanguage == 1) {
                            int keySize = FindKey.findKeySize(text);
                            System.out.println("O tamanho da chave é: " + keySize);
                            String foundKey = FindKey.findKey(text, keySize, portugueseLetterFrequencies);
                            System.out.println("A chave é: " + foundKey);
                            System.out.println("A mensagem decifrada: " + EncryptionDecryption.decrypt(text, foundKey));
                        } else if (choiceLanguage == 2) {
                            int keySize = FindKey.findKeySize(text);
                            System.out.println("O tamanho da chave é: " + keySize);
                            String foundKey = FindKey.findKey(text, keySize, englishLetterFrequencies);
                            System.out.println("A chave é: " + foundKey);
                            System.out.println("A mensagem decifrada: " + EncryptionDecryption.decrypt(text, foundKey));
                        }
                    }

                    break;

                case 5:
                    System.out.println("Encerrando o programa.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
