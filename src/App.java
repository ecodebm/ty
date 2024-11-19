import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Получаем данные от пользователя
        System.out.println("Введите ФИО:");
        String fullName = scanner.nextLine();

        System.out.println("Введите ИИН:");
        String iin = scanner.nextLine();

        System.out.println("Введите дату рождения (в формате ДД.ММ.ГГГГ):");
        String birthDate = scanner.nextLine();

        System.out.println("Введите название компании:");
        String companyName = scanner.nextLine();

        System.out.println("Введите адрес:");
        String address = scanner.nextLine();

        // Ваш шаблон текста договора
        String contractTemplate = "Я, %s, с ИИН %s, родившийся %s, проживаю по адресу %s, представляю компанию %s.\n"
                + "Настоящим подтверждаю согласие на выполнение обязательств согласно условиям договора.";

        // Заполняем шаблон данными
        String filledContract = String.format(contractTemplate, fullName, iin, birthDate, address, companyName);

        // Создаем и сохраняем Word-документ
        try (XWPFDocument document = new XWPFDocument()) {
            // Создаем новый параграф
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(filledContract);

            // Сохраняем документ в файл
            try (FileOutputStream out = new FileOutputStream("Contract.docx")) {
                document.write(out);
                System.out.println("Договор успешно создан: Contract.docx");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
