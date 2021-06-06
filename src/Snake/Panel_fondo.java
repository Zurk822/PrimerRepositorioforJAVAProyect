
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Panel_fondo extends JPanel{
    
    Color colorfondo=Color.gray;
    int tamax, tam, can, res;
    
    public Panel_fondo(int tamax, int can) {
    
        this.tamax=tamax;
        this.can=can;
        this.tam=tamax/can;
        this.res=tamax%can;
    }
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorfondo);
        for(int x=0;x<can;x++){
            for(int y=0;y<can;y++){
                pintor.fillRect((x*tam), (y*tam), tam-1, tam-1);
            }
        }
    }
    
}
