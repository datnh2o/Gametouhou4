package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import tklibs.SpriteUtils;

public class Enemy extends GameObject {
    public int hp;
    public Enemy(){
        image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hitBox = new BoxCollider(this, 28, 28);
        hp=3;
    }
    public void takeDamage(int damage){
        hp -= damage;
        if (hp<=0){
            hp=0;
            this.deactive();
        }
    }
    @Override
    public void run() {
        super.run();//velocity
        //System.out.println(velocity.x);
        if(this.onGoingRight() && this.outOfBoundRight()) {
            this.reserveVelocityX();
        }
        if(this.onGoingLeft() && this.outOfBoundLeft()) {
            this.reserveVelocityX();
        }
        this.deactiveIfNeeded();
    }
    @Override
    public void reset(){
        super.reset();
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hp=3;
    }

    private void deactiveIfNeeded() {
        if(position.y>600){
            this.deactive();
        }
    }

    private boolean outOfBoundLeft() {
        return position.x<0;
    }


    private boolean onGoingLeft() {
        return velocity.x<0;
    }

    private void reserveVelocityX() {
        velocity.x = -velocity.x;
    }

    private boolean outOfBoundRight() {
        return position.x > 384 - image.getWidth();
    }

    private boolean onGoingRight() {
        return velocity.x > 0;
    }
}
