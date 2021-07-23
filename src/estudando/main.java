package estudando;

public class main {
        public static void main(String[] args) throws InterruptedException {

                Filosofo filosofo = new Filosofo(5);
                for(int i = 0; i < filosofo.getFilosofos(); i++){
                        filosofo.getMesaFilosofo(i).philosopher(i);
                }

        }
}
