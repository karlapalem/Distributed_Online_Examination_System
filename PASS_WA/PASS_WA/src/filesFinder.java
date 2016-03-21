import java.io.File;
import java.io.IOException;

public class filesFinder {
public static void main(String[] args) throws IOException {
	File f = new File("C:\\WINDOWS\\chary.txt");
System.out.println(f.getFreeSpace());
    }
}
