public class usuario {
    public static void main(String[] args) throws Exception {
        SmartTv smarttv = new SmartTv();

        System.out.println("TV está ligada? " + smartTv.ligada);
        System.out.println("TV está no canal " + smartTv.canal);
        System.out.println("TV está no volume " + smartTv.volume);

    }
}
