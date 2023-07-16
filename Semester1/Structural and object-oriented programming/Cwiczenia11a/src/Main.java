import java.io.*;

public class Main {

    public static void kopiuj(String src, String dst){
        InputStream is = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(src);
            os = new FileOutputStream(dst);

            byte[] buf = new byte[1024];

            int bytesRead;

            while ((bytesRead = is.read(buf))>0){
                os.write(buf, 0, bytesRead);
            }
            is.close();
            os.close();

        }catch(IOException e){System.out.println("Error");}


    }

    public static void main(String[] args) {
        kopiuj("C:\\Users\\kamil\\IdeaProjects\\Cwiczenia11a\\in.txt", "C:\\Users\\kamil\\IdeaProjects\\Cwiczenia11a\\out.txt");
    }
}