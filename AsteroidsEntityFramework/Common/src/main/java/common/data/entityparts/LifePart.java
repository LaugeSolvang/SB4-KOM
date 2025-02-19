/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.data.entityparts;

import common.data.Entity;
import common.data.GameData;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;

    private boolean dead = false;


    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isDead() {
        return dead;
    }
    @Override
    public void process(GameData gameData, Entity entity) {
        if (isHit) {
            life = - 1;
            isHit = false;
        }
        if (life <= 0) {
            dead = true;
        }
    }
}
