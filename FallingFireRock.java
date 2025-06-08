import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FallingFireRock extends Actor {
    private static final int SPEED = 10;
    public FallingFireRock() {
        setImage("aste.png"); 
        escalarImagen(0.15); 
        setRotation(Greenfoot.getRandomNumber(360));
    }

    private void escalarImagen(double factorEscala) {
        GreenfootImage imagen = getImage();
        int nuevoAncho = (int) (imagen.getWidth() * factorEscala);
        int nuevoAlto = (int) (imagen.getHeight() * factorEscala);
        imagen.scale(nuevoAncho, nuevoAlto);
    }

    public void act() {
        setLocation(getX(), getY() + SPEED); 

        if (getY() > getWorld().getHeight() + getImage().getHeight() / 2) {
            getWorld().removeObject(this);
        }
    }
}