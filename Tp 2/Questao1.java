import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Questao1 {

    public static Scanner sc = new Scanner(System.in);

    public static class Personagem {

        private String nome;
        private int altura;
        private double peso;
        private String corDoCabelo;
        private String corDaPele;
        private String corDosOlhos;
        private String anoNascimento;
        private String genero;
        private String homeworld;

        //Contrutores da classe

        public Personagem(){
            this.nome = null;
            this.altura = -1;
            this.peso = -1;
            this.corDoCabelo = null;
            this.corDaPele = null;
            this.corDosOlhos = null;
            this.anoNascimento = null;
            this.genero = null;
            this.homeworld = null;

        }

        public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, 
                          String corDosOlhos, String anoNascimento, String genero, String homeworld){

            //setAtributos

        }

        //Métodos set
        private void setNome(String aux){
            this.nome = aux;
        }

        private void setAltura(int aux){
            this.altura = aux;
        }

        private void setPeso(Double aux){
            this.peso = aux;
        }

        private void setCorDoCabelo(String aux){
            this.corDoCabelo = aux;
        }

        private void setCorDaPele(String aux){
            this.corDaPele = aux;
        }

        private void setCOrDosOlhos(String aux){
            this.corDosOlhos = aux;
        }

        private void setAnoNascimento(String aux){
            this.anoNascimento = aux;
        }

        private void setGenero(String aux){
            this.genero = aux;
        }

        private void setHomeworld(String aux){
            this.homeworld = aux;
        }

        //Métodos get
        public String getNome(){
            return this.nome;
        }
    
        public int getAltura(){
            return this.altura;
        }
    
        public double getPeso(){
            return this.peso;
        }
    
        public String getCorDoCabelo(){
            return this.corDoCabelo;
        }
    
        public String getCorDaPele(){
            return this.corDaPele;
        }
    
        public String getCorDosOlhos(){
            return this.corDosOlhos;
        }
    
        public String getAnoNascimento(){
            return this.anoNascimento;
        }
    
        public String getGenero(){
            return this.genero;
        }
    
        public String getHomeworld(){
            return this.homeworld;
        }

                /*
        *Imprimi os atributos da classe Pessoa no arquivo
        */
        public void imprimir(){
            System.out.println(" ## " +
                         this.nome + " ## " +
                         this.altura + " ## " +
                         String.valueOf(this.peso).replace(".0", "")
                         + " ## " + 
                         this.corDoCabelo + " ## " +
                         this.corDaPele + " ## " +
                         this.corDosOlhos + " ## " +
                         this.anoNascimento + " ## " +
                         this.genero + " ## " +
                         this.homeworld + " ## ");
      }

        //Métodos de leitura
        public String ler(String path){

            FileReader fr = null;
            BufferedReader br = null;
            String line = null;
            String linha = null;

            try {
                fr = new FileReader(path);
                br = new BufferedReader(fr);
                line = br.readLine();

            while(line != null){
                    linha = line;
                    line = br.readLine();
                    linha += line;
                }

            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
            }

            return linha;
           
        }

        //Método separar o que precisa
        public String cortarEntrada(String entrada){

            int posicaoI = 0, posicaoF = 0;
            String texto = null;

            posicaoI = entrada.indexOf("name");
            posicaoF = entrada.indexOf(", 'films");

            texto = entrada.substring(posicaoI, posicaoF);

            return texto;

            }

            public void separandoString(String entrada){

                String [] informacoes = new String[9];
                String [] respostas = new String[9];
                int altura = 0;
                double peso = 0;
    
                informacoes = entrada.split("',");
    
                for(int i = 0; i < informacoes.length; i++){
                   respostas[i] = tratarInformacoes(informacoes[i]);               
                }

                altura = mudarAltura(respostas[1]);
                peso = mudarPeso(respostas[2]);

                setNome(respostas[0]);
                setAltura(altura);
                setPeso(peso);
                setCorDoCabelo(respostas[3]);
                setCorDaPele(respostas[4]);
                setCOrDosOlhos(respostas[5]);
                setAnoNascimento(respostas[6]);
                setGenero(respostas[7]);
                setHomeworld(respostas[8]);

            }

            public int mudarAltura(String entrada){

                int altura = 0;

                if(entrada.equals("unknown"))entrada = "0";

                altura = Integer.parseInt(entrada);

                return altura;

            }

            public double mudarPeso(String entrada){
                //double

                double peso = 0;

                entrada = entrada.replace(",", "");

                if(entrada.equals("unknown"))entrada = "0";

                peso = Double.parseDouble(entrada);

                //String.valueOf(peso).replace(".0", "");

                

                return peso;

            }

            
            
            public String tratarInformacoes(String entrada){
    
                int posicaoI = 0;
    
                posicaoI = entrada.indexOf(": '");
    
                entrada = entrada.substring(posicaoI, entrada.length());
                entrada = entrada.replace(": '", "");
                entrada = entrada.replace("'", "");

                //System.out.println(entrada);
    
                return entrada;  

            }

                public void inicio(String entrada){

                    entrada = cortarEntrada(entrada);
                    separandoString(entrada);


                }

    }// fim classe Personagem

    public static void main(String [] args){

        MyIO.setCharset("UTF-8");

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);
            personagem.imprimir();
        }

        


    }


}