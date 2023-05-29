import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        String tempInput = input();
        String fileName = nameCheck(tempInput, 0);
        String path = fileName + ".txt";
        String content = "<" + fileName + ">" + "<" + nameCheck(tempInput, 1) + ">" +
                "<" + nameCheck(tempInput, 2) + ">" + "<" + birth(tempInput) + ">" + "<" +
                phoneNumber(tempInput)  + ">" + "<" + sex(tempInput) + ">";
        try(FileWriter writer = new FileWriter(path, true))
        {
            writer.write(content);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static String[] dividedInput(String data) {   // Разделение данных после ввода
        String[] dividedInput = data.split(" ");
        return dividedInput;
    }

    public static String inputInformation() { // Запрашиваемые данные для ввода
        String string = "Фамилия Имя Отчество датарождения номертелефона пол";
        return string;
    }

    public static int inputInformationCount() { // Подсчет запрашиваемых данных для ввода. Подсчет для сверки с введенными данными.
        String infCount = inputInformation();
        String[] DivInfCount = dividedInput(infCount);
        int InputInfCount = DivInfCount.length;
        ;
        return InputInfCount;
    }

    public static boolean LessDataCheck(String data) { // Проверка введеных данных, ошибка если не все введено
        boolean check = true;
        String[] input = dividedInput(data);
        int count = input.length;
        if (count < inputInformationCount()) {
            check = false;
        }
        return check;
    }

    public static boolean MoreDataCheck(String data) { // Проверка введеных данных, ошибка если больше введено
        boolean check = true;
        String[] input = dividedInput(data);
        int count = input.length;
        if (count > inputInformationCount()) {
            check = false;
        }
        return check;
    }
    public static boolean symbolsCheck(String data) { // Проверка фио на символы и знаки
        boolean symNumCheck = true;
        String symNums = "]!#$%&()*+,./:;<=>?@[^_{|}~1234567890";
        String[] symCheckInput = data.split("");
        for (int i = 0; i < symCheckInput.length; i++){
            if(symNums.contains(symCheckInput[i])){
                symNumCheck = false;
                break;
            }
        }
        return symNumCheck;
    }

    public static boolean numbersCheck(String data) { // Проверка телефона на наличие отличных от цифр символов
        boolean numCheck = true;
        String numbers = "1234567890";
        String[] numCheckInput = data.split("");
        for (int i = 0; i < numCheckInput.length; i++){
            if(!numbers.contains(numCheckInput[i])){
                numCheck = false;
                break;
            }
        }
        return numCheck;
    }

    public static String nameCheck(String inputString, int position) throws RuntimeException { // проверка фио
        String[] data = dividedInput(inputString);
        String name = data[position];
        if (name.length() == 0) {
            try {
                throw new RuntimeException("Введена пустая строка в фио");
            } catch (Exception e) {
                System.out.println("Введена пустая строка в фио");
                throw e;
            }
        } else if (name == " ") {
            try {
                throw new RuntimeException("Введена пустая строка в фио");
            } catch (Exception e) {
                System.out.println("Фамилия/имя/отчество не может быть пустым");
                throw e;
            }
        } else if (!symbolsCheck(name)) {
            try {
                throw new RuntimeException("Содержит символы или цифры");
            } catch (Exception e) {
                System.out.println("Фамилия/имя/отчество не может содержать цифры или символы.");
                throw e;
            }
        }
        return name;
    }
    public static long phoneNumber(String inputString) throws RuntimeException { // проверка телефона
        String[] data = dividedInput(inputString);
        String phone = data[4];
        if (phone.length() == 0) {
            try {
                throw new RuntimeException("Введена пустая строка в номер телефона");
            } catch (Exception e) {
                System.out.println("Введена пустая строка в номер телефона");
                throw e;
            }
        } else if (phone == " ") {
            try {
                throw new RuntimeException("Введена пустая строка в номер телефона");
            } catch (Exception e) {
                System.out.println("Номер телефона не может быть пустым");
                throw e;
            }
        } else if (!numbersCheck(phone)) {
            try {
                throw new RuntimeException("Номер телефона содержит отличные от цифр символы");
            } catch (Exception e) {
                System.out.println("Номер телефона может содержать только цифры.");
                throw e;
            }
        } else if (phone.length() != 11) {
            try {
                throw new RuntimeException("Некорректная длина номера телефона");
            } catch (Exception e) {
                System.out.println("Номер телефона должен содержать 11 цифр.");
                throw e;
            }
        }
        long phoneNum = Long.parseLong(phone);
        return phoneNum;
    }

    public static boolean sexLettersCheck(String data) { // Проверка пола на некорректные буквы
        boolean sexLettersCheck = true;
        String sexVar = "fm";
        int counter = 0;
        String[] sexInput = data.split("");
        for (int i = 0; i < sexInput.length; i++){
            if(sexVar.contains(sexInput[i])){
                counter += 1;
            }
        }
        if(counter != 1){
            sexLettersCheck = false;
        }
        return sexLettersCheck;
    }
    public static char sex(String inputString) throws RuntimeException { // проверка пола
        String[] data = dividedInput(inputString);
        String sexString = data[5];
        if (sexString.length() == 0) {
            try {
                throw new RuntimeException("Введена пустая строка в пол");
            } catch (Exception e) {
                System.out.println("Введена пустая строка в пол");
                throw e;
            }
        } else if (sexString == " ") {
            try {
                throw new RuntimeException("Введена пустая строка в пол");
            } catch (Exception e) {
                System.out.println("Введена пустая строка в пол");
                throw e;
            }
        } else if (sexString.length() != 1) {
            try {
                throw new RuntimeException("Пол должен состоять из 1 символа");
            } catch (Exception e) {
                System.out.println("Пол должен состоять из 1 символа");
                throw e;
            }
        } else if (!sexLettersCheck(sexString)) {
            try {
                throw new RuntimeException("Некорректный пол");
            } catch (Exception e) {
                System.out.println("Пол должен состоять из символа f или m");
                throw e;
            }
        }
        char sexChar = sexString.charAt(0);
        return sexChar;
    }
    public static boolean birthDotCheck(String data) { // Проверка разделителей даты рождения
        boolean birthDCheck = true;
        String symv = ".";
        String[] birthD = data.split("");
        int counter = 0;
        for (int i = 0; i < birthD.length; i++){
            if(symv.contains(birthD[i])){
                counter+=1;
            }
        }
        if (counter!=2){
            birthDCheck = false;
        }
        return birthDCheck;
    }
    public static boolean monthCheck(String data) { // Проверка месяца даты рождения
        boolean monthCheck = true;
        String[] birthOne = data.split("");
        String monthString = birthOne[3] + birthOne[4];
        int monthInt = Integer.parseInt(monthString);
        if (monthInt < 1){
            monthCheck = false;
        } else if (monthInt > 12) {
            monthCheck = false;
        }
        return monthCheck;
    }

    public static boolean dayCheck(String data) { // Проверка дня даты рождения
        boolean dayCheck = true;
        String[] birthArray = data.split("");
        String monthString = birthArray[3] + birthArray[4];
        int monthInt = Integer.parseInt(monthString);
        String dayString = birthArray[0] + birthArray[1];
        int dayInt = Integer.parseInt(dayString);
        if (dayInt < 1){
            dayCheck = false;
        } else if (dayInt > 31) {
            dayCheck = false;
        } else if (monthInt == 2) {
            if (dayInt > 29){
                dayCheck = false;
            }
        } else if (monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11) {
            if (dayInt > 30){
                dayCheck = false;
            }
        }
        return dayCheck;
    }

    public static boolean yearCheck(String data) { // Проверка года даты рождения
        boolean yearCheck = true;
        String[] birthArray = data.split("");
        String  yearString = birthArray[6] + birthArray[7] + birthArray[8] + birthArray[9];
        int yearInt = Integer.parseInt(yearString);
        int curYear = Year.now().getValue();
        if (yearInt < 1900){
            yearCheck = false;
        } else if (yearInt > curYear) {
            yearCheck = false;
        }
        return yearCheck;
    }
    public static String birth(String inputString) throws RuntimeException {
        String[] data = dividedInput(inputString);
        String dateOfBirth = data[3];
        if (dateOfBirth.length() == 0) {
            try {
                throw new RuntimeException("Введена пустая строка в дату рождения");
            } catch (Exception e) {
                System.out.println("Введена пустая строка в дату рождения");
                throw e;
            }
        } else if (dateOfBirth == " ") {
            try {
                throw new RuntimeException("Введена пустая строка в дату рождения");
            } catch (Exception e) {
                System.out.println("Дата рождения не может быть пустой");
                throw e;
            }
        } else if (dateOfBirth.length()!=10) {
            try {
                throw new RuntimeException("Некорректная длина даты рождения");
            } catch (Exception e) {
                System.out.println("Дата рождения должна состоять из 10 символов: 2 день, 2 месяц, 4 год, 2 разделителя");
                throw e;
            }
        } else if (!birthDotCheck(dateOfBirth)) {
            try {
                throw new RuntimeException(" Нет разделителя в дате рождения");
            } catch (Exception e) {
                System.out.println("Разделитель в дате рождения должна быть точка");
                throw e;
            }
        } else if (!monthCheck(dateOfBirth)) {
            try {
                throw new RuntimeException("Некорректный месяц даты рождения");
            } catch (Exception e) {
                System.out.println("Месяц даты рождения должен быть от 01 до 12");
                throw e;
            }
        } else if (!dayCheck(dateOfBirth)) {
            try {
                throw new RuntimeException("Некорректный день даты рождения");
            } catch (Exception e) {
                System.out.println("Некорректный день даты рождения");
                throw e;
            }
        } else if (!yearCheck(dateOfBirth)) {
            try {
                throw new RuntimeException("Некорректный год даты рождения");
            } catch (Exception e) {
                System.out.println("Некорректный год даты рождения");
                throw e;
            }
        }
        return dateOfBirth;
    }

    public static String input() throws RuntimeException {
        String inputData = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите следующие данные:");
            System.out.println(inputInformation());
            System.out.println("Дата рождения в формате dd.mm.yyyy");
            System.out.println("Номер телефона в формате 79999999999 (11 знаков)");
            System.out.println("пол - f или m");
            System.out.print("Ввод данных: ");
            inputData = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (inputData.length() == 0) {
            try {
                throw new RuntimeException("Введена пустая строка");
            } catch (Exception e) {
                System.out.println("Введена пустая строка");
                throw e;
            }
        } else if (!LessDataCheck(inputData)) {
            try {
                throw new RuntimeException("Введены не все данные");
            } catch (Exception e) {
                System.out.println("Вы ввели меньше данных, чем требуется");
                throw e;
            }
        } else if (!MoreDataCheck(inputData)) {
            try {
                throw new RuntimeException("Введены лишние данные");
            } catch (Exception e) {
                System.out.println("Вы ввели больше данных, чем требуется");
                throw e;
            }
        }
        return inputData;
    }
}