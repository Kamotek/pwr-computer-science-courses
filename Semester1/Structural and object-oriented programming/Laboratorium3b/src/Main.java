import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj wielkość");
        int iloscPieter = input.nextInt();

        for (int iteratorSegmentow = 0; iteratorSegmentow < iloscPieter; iteratorSegmentow++) {
            for (int iteratorPieter = 0; iteratorPieter <= iteratorSegmentow; iteratorPieter++) {
                for (int iteratorZnakowWyrownania = iloscPieter - iteratorPieter; iteratorZnakowWyrownania > 0; iteratorZnakowWyrownania--) {
                    System.out.print(" ");
                }
                for (int iteratorZnakowWPoziomie = 0; iteratorZnakowWPoziomie <= iteratorPieter * 2; iteratorZnakowWPoziomie++) {
                    if(iteratorPieter%2==0) {
                        System.out.print("x");
                    }
                    else {
                        System.out.print("o");
                    }
                }

                System.out.println("");
            }
        }

    }
}