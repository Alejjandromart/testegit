package smartTv;

public class SmartTv {
    boolean ligada = false;
    int canal = 1;
    int volume = 25;

    public void ligar(){
        ligada = true;
    }
    public void desligar(){ 
        ligada = false;
    }

    public void mudarCanalMais(){
        canal++;
    }
    public void mudarCanalMenos(){
        canal --;
    }
    public void escolherCanal6(){
        canal = 6;
    }

    public void aumentarVolume(){
        volume ++;
    }
    public void diminuirVolume(){
        volume --;
    }
}