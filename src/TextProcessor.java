import java.util.Scanner;

public class TextProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer text = new StringBuffer();

        System.out.println("Введіть текст(анг.розкладка):");
        text.append(scanner.nextLine());

        while (true) {
            System.out.println("Введіть довжину приголосного слова для видалення (або натисніть Enter для виходу):");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Вихід...");
                break;
            }

            int wordLength;
            try {
                wordLength = Integer.parseInt(input);
                if (wordLength <= 0) {
                    throw new NumberFormatException("Довжина слова повинна бути > 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильне введеня. Будь-ласка введіть позитивне число.");
                continue;
            }

            boolean wordsRemoved = removeWords(text, wordLength);

            if (!wordsRemoved) {
                System.out.println("Немає слів заданої довжини " + wordLength + ", які посинаються з приголосної букви.");
                System.out.println("Що обереш ти?:");
                System.out.println("1. Спробувати іншу задану довжину");
                System.out.println("2. Ввести новий текст");
                System.out.println("Натисніть Enter для виходу.");

                String choice = scanner.nextLine();

                if (choice.isEmpty()) {
                    System.out.println("Вихід...");
                    break;
                } else if (choice.equals("1")) {
                    continue;
                } else if (choice.equals("2")) {
                    System.out.println("Введіть ваш новий текст:");
                    text.setLength(0);
                    text.append(scanner.nextLine());
                } else {
                    System.out.println("Неправильне введення. Вихід з програми.");
                    break;
                }
            } else {
                System.out.println("Оновлений текст:");
                System.out.println(text);
            }
        }

        scanner.close();
    }

    private static boolean removeWords(StringBuffer text, int wordLength) {
        String vowels = "AEIOUaeiou";
        String[] words = text.toString().split("\\s+");
        boolean wordRemoved = false;

        StringBuffer newText = new StringBuffer();

        for (String word : words) {
            if (word.length() != wordLength || vowels.indexOf(word.charAt(0)) != -1) {
                newText.append(word).append(" ");
            } else {
                wordRemoved = true;
            }
        }

        if (wordRemoved) {
            text.setLength(0); // Clear the original text
            text.append(newText.toString().trim());
        }

        return wordRemoved;
    }
}