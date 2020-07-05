
import java.io.IOException;
import java.io.RandomAccessFile;

public class TesteQ8 {

    public static void escrever(int quantidade) {

        double numeros;

        try{
            RandomAccessFile arquivo = new RandomAccessFile("Q8.txt", "rw");

            for(int i = 0; i < quantidade; i++){
                numeros = MyIO.readDouble();
                arquivo.writeDouble(numeros);
            }
            arquivo.close();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static void leituraTras(int quantidade) {

        double numeros;

        try{
            RandomAccessFile arquivo = new RandomAccessFile("Q8.txt", "r");

            for(int i = 1; i <= quantidade; i++){
                arquivo.seek(arquivo.length() - (i * 8));
                numeros = arquivo.readDouble();

                if(numeros == (int)numeros){
                    MyIO.println((int)numeros);
                }
                else{
                    MyIO.println(numeros);
                }
            }

            arquivo.close();
        }

        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        int quantidade = MyIO.readInt();
        escrever(quantidade);
        leituraTras(quantidade);    
        
     
      }
 
}
