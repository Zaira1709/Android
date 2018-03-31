package figuras;

/**
 *
 * @author zaira
 */
public class Fig {
   int height = 10;
   int width = 10;
   int x=0;
  
    public void dibujarCuadrado(int height){
          this.height = height;
          System.out.println("Cuadrado");
          for(int row = 1; row <= height; row++){
              for (int col = 1; col <= height; col++){
                   System.out.print("@ ");
              }
              System.out.println();
          }
    }
    
    public void dibujaRectangulo(int height, int width ){
       this.height = height;
       this.width = width;
       System.out.println("Rectangulo");
       for(int row = 1; row <= height; row++){
            for (int col = 1; col <= width; col++){
                 System.out.print("*");
            }
            System.out.println();
        }
    }
    public void dibujaTrianguloEquilatero(int x){
       System.out.println("Triangulo equilatero");
       this.x = x;
       int i=0;
        for (i = 1; i <= this.x; i++){
            for (int k = i; k >= 1; k--) {
                System.out.print("* ");
            }
            System.out.println("");
        }
        System.out.println();    
    }
    
    public void dibujarTrianguloEscaleno(int x ){
        this.x = x-1;
        int y = 0;
        int i=0;
        for(i= 0; i<=x; i++){
            for( this.x= x; this.x >=i; this.x-- ){
                System.out.print(" ");
            }
            for(y= 0; y<= (i + i); y++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void dibujarRombo(int x, int y){
        int k=0;
        System.out.println("Rombo");
        Fig fig= new Fig();
        fig.dibujarTrianguloEscaleno(x);
        this.x = x-1;
        int w= 7;
        for(int i=w;i>=1;i=i-2){
            for(k=i;k<=w+2;k=k+2){
                System.out.print(" ");
            }
            for(int j=i-2;j>0;j--){
                System.out.print("*");
            }
            System.out.println();
        }           
    }
    
    public void dibujarHexagono(int x ){
        System.out.println("Hexagono");
        this.x = x-1;
        int  y = 0;
        for(int i= 1; i<=x; i++){
            for( this.x= x; this.x >=i; this.x-- ){
                System.out.print(" ");
            }
            for(y= 0; y<= (i + i); y++){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i= x-1; i>=1; i--){
            for( this.x= x; this.x >=i; this.x-- ){
                System.out.print(" ");
            }
            for(y= (i + i); y>= 0; y--){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }
    
     public void dibujaroctagono(int x ){
        System.out.println("Octagono");
        this.x = x-1;
        int  y = 0, k=0, w=7;

        for(int i= 1; i<=x; i++){
            for( this.x= x; this.x >=i; this.x-- ){
                System.out.print(" ");
            }
            for(y= 0; y<= (i + i); y++){
                System.out.print("*");
            }
            System.out.println();
        }
        
        for(int i= x; i>=1; i--){
            for( this.x= x; this.x >=i; this.x-- ){
                System.out.print(" ");
            }
            for(y= (i + i); y>= 0; y--){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();   
    }
}
