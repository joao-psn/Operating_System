package estudando;

public class Filosofo {

    private int filosofos;
    private Mesa mesa[];

    public Filosofo(int N){
        this.filosofos = N;
        this.mesa = new Mesa[N];
        populaMesa();
    }

    public void populaMesa(){
        for (int i = 0; i < filosofos; i++){
            System.out.println(i);
            this.mesa[i] = new Mesa(filosofos, i);
        }
    }

    public int getFilosofos(){
        return filosofos;
    }

    public Mesa getMesaFilosofo(int i){
        return mesa[i];
    }
}
