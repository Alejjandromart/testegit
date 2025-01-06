package smartTv;

public class usuario {
    public static void main (String[] args){
        SmartTv smartTv = new SmartTv();

        System.out.println("TV está ligada? " + smartTv.ligada);
        System.out.println("TV está no canal: " + smartTv.canal);
        System.out.println("TV está no volume: " + smartTv.volume + "\n");

        System.out.println("Alterações");
        smartTv.ligar();
        System.out.println("TV está ligada? " + smartTv.ligada + "\n");

        smartTv.mudarCanalMais();
        System.out.println("TV está no canal: " + smartTv.canal);

        smartTv.mudarCanalMenos();
        System.out.println("TV está no canal? " + smartTv.canal);

        smartTv.escolherCanal6();
        System.out.println("Colocar a TV no canal: " + smartTv.canal + "\n");

        smartTv.aumentarVolume();
        System.out.println("TV está no volume: " + smartTv.volume);

        smartTv.diminuirVolume();
        System.out.println("TV está no volume: " + smartTv.volume + "\n");
    }
}