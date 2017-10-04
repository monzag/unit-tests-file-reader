import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FilePartReader {

    private String filePath;
    private int fromLine;
    private int toLine;

    public FilePartReader() {
        this.filePath = "";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, int fromLine, int toLine) throws IllegalArgumentException{
        if (toLine < fromLine || fromLine < 0) {
            throw new IllegalArgumentException();
        }

        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    private String read() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        String content = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return content;
    }

    public String readLines() throws FileNotFoundException {
        String result = "";
        try {
            String content = read();
            String[] lines = content.split("\r\n");

            if (fromLine == toLine){
                return lines[fromLine - 1];
            }

            for (int i = fromLine - 1; i < toLine; i++) {
                result += lines[i] + "\n";
            }
            return result.trim();

        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

//    public static void main(String[] args) {
//        try {
//            FilePartReader filePartReader = new FilePartReader();
//            filePartReader.setup("tests/test_data.txt", 1, 2);
//            String result = filePartReader.readLines();
//            System.out.println(result);
//            System.out.println("1a1\n" +
//                    "2b 2a");
//            System.out.println(";l");
//        } catch (FileNotFoundException e) {
//
//        }
//    }
}
