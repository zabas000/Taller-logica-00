public class Robot {
    private int identificador;
    private int x1;
    private int y1;
    private int energiaDispo;

    public Robot (int identificador, int x1 , int y1, int energiaDispo){
        this.identificador = identificador;
        this.x1 = x1;
        this.y1 = y1;
        this.energiaDispo = energiaDispo;
    }

    
    //Distancia = |x destino − x origen| + |y destino − y origen|
    public int distanciaHasta(int xDes, int yDes) {
        return Math.abs(xDes - this.x1) + Math.abs(yDes - this.y1);
    }


    public boolean moverA(int x, int y){
        int dis = distanciaHasta(x, y);
        if(dis <= energiaDispo){
            energiaDispo -= dis;
            x1 = x;
            y1 = y;
            return true;
        }else{
            return false;
        }
    }


//a.transene


    public boolean transferirEnergia(Robot receptor , int cantidad){
        if(receptor == null || receptor.identificador == identificador){
            return false;
        }
        if(x1 != receptor.x1 || y1 != receptor.y1){
            return  false;
        }
        if(cantidad <= 0 || energiaDispo < cantidad){
            return false;
        }
        if(receptor.energiaDispo + cantidad > 100){
            return false;
        }
        energiaDispo = energiaDispo - cantidad;
        receptor.energiaDispo = receptor.energiaDispo + cantidad;
        return true;
    }


//mini-metodos
    public int getIdentificador(){
        return identificador;
    }

    public int getEnergiaDisponible(){
        return energiaDispo;
    }
    
    public int getX1(){
        return x1;
    }

    public int getY1(){
        return y1;
    }

    public String getEstado(){
        return "Robot: " + identificador
            + "  Posicion: (" + x1 + ", " + y1 + ")"
            + "  Energia: " + energiaDispo;
    }
}