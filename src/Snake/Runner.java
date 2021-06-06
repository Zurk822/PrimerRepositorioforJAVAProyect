package Snake;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Runner implements Runnable{

    PanelSnake panel;
    boolean estado=true;
    
    public Runner(PanelSnake panel) {
        this.panel=panel;
    }
    
    @Override
    public void run() {
        while(estado){
            panel.avanzar();
            panel.repaint();

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    public void stop() {
        this.estado=false;
    }
}
