package estudando;

import java.util.concurrent.Semaphore;

public class Mesa {

    /* Quantidade de Filósofos*/
    private int N;

    /*Número de visinhos a esquerda de um filósofo*/
    private int LEFT;

    /*Número de visinhos a direita de um filósofo*/
    private int RIGHT;

    /*Estado de um filósofo quando está pensando*/
    static final int THINKING = 0;

    /*Estado de um filósofo com fome, logo está tentando pegar talheres*/
    static final int HUNGRY = 1;

    /*Estado de um filósofo quando está comendo*/
    static final int EATING = 2;

    /*Lista de estado dos filosofos*/
    private int state[];

    /*Exclusão mútua para as regiões críticas*/
    private Semaphore mutex;

    /*Um semáforo por filosofo*/
    private Semaphore s[];

    public Mesa (int N, int i) {
        this.N = N;
        this.LEFT = (i + this.N - 1) % this.N;
        this.RIGHT = (i + 1) % this.N;
        this.state = new int[this.N];
        this.mutex = new Semaphore(1);
        this.s = new Semaphore[this.N];
        populaS();
    }

    public void philosopher(int i) throws InterruptedException {
        while (true){
            think();
            take_forks(i);
            eat();
            put_forks(i);
        }
    }

    public void take_forks(int i) throws InterruptedException {
        mutex.release();
        state[i] = HUNGRY;
        test(i);
        mutex.acquire();
        s[i].release();
    }

    public void put_forks(int i) throws InterruptedException {
        mutex.release();
        state[i] = THINKING;
        test(LEFT);
        test(RIGHT);
        mutex.acquire();
    }

    public void test(int i) throws InterruptedException {
        if(state[i] == HUNGRY && state[LEFT] != EATING && state[RIGHT] != EATING){
            state[i] = EATING;
            s[i].acquire();
        }
    }

    private String eat() {
        String variable = "Comendo";
        System.out.println(variable);
        return variable;
    }

    private String think() {
        String variable = "Estou pensando";
        System.out.println(variable);
        return variable;
    }

    public void populaS(){
        for (int i = 0; i < s.length; i++){
            this.s[i] = mutex;
        }
    }
}
