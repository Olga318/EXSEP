//Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
//        Фамилия Имя Отчество датарождения номертелефона пол
//
//        Форматы данных:
//        фамилия, имя, отчество - строки
//        датарождения - строка формата dd.mm.yyyy
//        номертелефона - целое беззнаковое число без форматирования
//        пол - символ латиницей f или m.
//
//        Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
//
//        Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
//
//        Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
//
//<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//
//        Не забудьте закрыть соединение с файлом.
//
//        При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            makeRecord();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeRecord() throws Exception {
        System.out.println("Введите фамилию, имя, отчество, дату рождения (в формате dd.mm.yyyy), номер телефона (число без разделителей) пол (латинскими буквами f или m");

        String text;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            text = bf.readLine();
        } catch (IOException e) {
            throw new Exception("Произошла ошибка при работе с консолью");
        }

        String[] array = text.split(" ");
        if (array.length != 6) {
            throw new Exception("Введено неверное количество параметров");
        }
        String surname = array[0];
        String name = array[1];
        String patronymic = array[2];

        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date birthDate;
        try {
            birthDate = format.parse(array[3]);
        } catch (ParseException e) {
            throw new ParseException("Неверный формат даты рождения", e.getErrorOffset());
        }

        int phoneNumber = 0;
        try {
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат номера телефона");
        }

        String gender = array[5];
        if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")) {
            throw new RuntimeException("Неверно введен пол");
        }


        try {

            FileWriter fileWriter = new FileWriter(surname + ".txt");
            fileWriter.write(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender + System.getProperty("line.separator") + "\n");

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Ошибка! Не удалось создать фаил.");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка! Введены неполные данные.");
            return;
        }
        System.out.println("Данные успешно сохранены в файле" + " " + array[0] + "/txt");

    }
}