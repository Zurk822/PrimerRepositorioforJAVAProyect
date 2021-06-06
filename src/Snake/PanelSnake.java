/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author intel
 */
public class PanelSnake extends JPanel{
    
    public static Vista vista;
    
    public static void main(String[] args){
        vista= new Vista();
        vista.setVisible(true);
    } 
    
    Color colorSnake=Color.pink;
    Color colorcomida=Color.red;
    int tamax, tam, can, res;
    String direc="de";
    
    List<int[]>snake=new ArrayList<>();
    int comida[]= new int[2];
    
    String NextDirec="de";
    
    Thread hilo;
    Runner run;
    
    public PanelSnake(int tamax, int can) {
    
        this.tamax=tamax;
        this.can=can;
        this.tam=tamax/can;
        this.res=tamax%can;
        
        int[] a={can/2-1, can/2-1};
        int[] b={can/2, can/2-1};
        
        snake.add(a);
        snake.add(b);
        
        generarcomida();
        
        run=new Runner(this);
        hilo= new Thread(run);
        hilo.start();
        
    }
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorSnake);
        //Snake
        for(int x=0;x<snake.size();x++){
            
            pintor.fillRect((snake.get(x)[0]*tam), (snake.get(x)[1]*tam), tam-1, tam-1);
            
        }
        //Food
        pintor.setColor(colorcomida);
        pintor.fillRect(comida[0]*tam, (comida[1]*tam), tam-1, tam-1);

    }
    public void avanzar(){
        
        igualardirec();
        
        int[] last= snake.get(snake.size()-1);
        int x=0, y=0;
        switch(direc){
            case "de":x=1;
                break;
            case "iz":x=-1;
                break;
            case "ar":y=-1;
                break;
            case "ab":y=1;
                break;
        }
        int[] nuevo={Math.floorMod(last[0]+x, can), Math.floorMod(last[1]+y, can)};
        boolean exist=false;
        for(int w=0;w<snake.size();w++){
            
            if( nuevo[0]==snake.get(w)[0] && nuevo[1]==snake.get(w)[1]){
                exist=true;
                break;
            }
         
        }
        if(exist){
            PanelSnake PS=new PanelSnake(1,1);
            JOptionPane.showMessageDialog(null, "Game Over");
            PS.setCount("true");
            try{
                if(PS.getCount().equals("true")){

                }
            }catch(java.lang.StackOverflowError e){
                
            }
        }else{
            if( nuevo[0]==comida[0] && nuevo[1]==comida[1]){
                snake.add(nuevo);
                generarcomida();
                
            }else{
                snake.add(nuevo);
                snake.remove(0);
            }
        }
        
        
    }
    
    public void generarcomida(){
        boolean exist=false;
        int a= (int) (Math.random()*can);
        int b= (int) (Math.random()*can);
        for(int[] par:snake){
            if (par[0]==a && par[1]==b){
                exist=true;
                generarcomida();
                break;
            }
        }
        if(!exist){
            this.comida[0]=a;
            this.comida[1]=b;
        }
        
    }
    
    public void changedirec(String direc) {
        try{
            if((this.direc.equals("de") || this.direc.equals("iz")) && (direc.equals("ar") || direc.equals("ab"))){
                this.NextDirec=direc;
            }else if((this.direc.equals("de") || this.direc.equals("iz")) && (direc.equals("de") || direc.equals("iz"))){
                this.NextDirec=null;
            }

            if((this.direc.equals("ar") || this.direc.equals("ab")) && (direc.equals("iz") || direc.equals("de"))){
                this.NextDirec=direc;
            }else if((this.direc.equals("ar") || this.direc.equals("ab")) && (direc.equals("ar") || direc.equals("ab"))){
                this.NextDirec=direc;
            }
        }catch(java.lang.NullPointerException e){
            
        }
    }
    
    public void igualardirec() {
        this.direc=this.NextDirec;
    }
    
    public static String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
