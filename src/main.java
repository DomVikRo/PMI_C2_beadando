import java.util.Scanner;

public class main {
    public static Scanner Scanner=new Scanner(System.in);
    public static void valaszt(){
        int i=0;
        System.out.println("Kérlek válasz a rendelkezésre álló funkciók között(egy szám megadása szükséges a lentiek közül): ");
        System.out.println("0. diákok adatainak lekérése.");
        System.out.println("1.Meglévő adatok átírása;");
        System.out.println("2. új diák hozzáadása.");
        System.out.println("3. jelenleg létező diák eltávolítása.");
        System.out.println("4. Kilépés és mentés.");
        System.out.print("A kívánt művelet számát ide írja: ");
        i=Scanner.nextInt();

        if(i==0) {
            System.out.println(com.kiír());
            valaszt();
        }else if(i==1){
            com.update();
        }else if(i==2){
            com.hozzaad();
        }else if(i==3){
            System.out.print("\nKérem a törölni kívánt diák nevét: ");
            String nev=com.inputname();
            com.delete(nev);
        }else if(i==4){
            System.out.println("\n Köszönöm hogy a programomat használta.");
            com.save();
            System.exit(0);
        }else{
            System.out.println("\n Az önáltal megadott érték nem szerepel a listában, kérem vállaszon a listából egy értéket.\n");
            valaszt();
        }
    }

    public static void main(String args[])
    {
        com.beolvas();
        valaszt();
    }




}

