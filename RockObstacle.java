import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RockObstacle extends Actor {
    private static final int SPEED = 4;

    public RockObstacle() {
        setImage("roca.png"); 
        escalarImagen(0.15); 
    }
    
    private void escalarImagen(double factorEscala) {
        GreenfootImage imagen = getImage();
        int nuevoAncho = (int) (imagen.getWidth() * factorEscala);
        int nuevoAlto = (int) (imagen.getHeight() * factorEscala);
        imagen.scale(nuevoAncho, nuevoAlto);
    }

    public void act() {
        move(-SPEED);
        if (getX() < -getImage().getWidth() / 2) {
            getWorld().removeObject(this);
        }
    }
}