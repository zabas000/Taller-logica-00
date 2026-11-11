public class EjemploVoid {
    public static void main(String[] args) {
        imprimir();
        int c;
        c=calcularsuma(5, 7);
        c=c*9;
        System.out.println("El resultado es: " + c);
        
    }

    public static void imprimir(){
        System.out.println("Hola");    
    }

    public static int calcularsuma(int a, int b){
        return a + b;
    }
}
