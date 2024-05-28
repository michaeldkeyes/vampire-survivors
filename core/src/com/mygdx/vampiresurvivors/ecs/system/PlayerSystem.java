package com.mygdx.vampiresurvivors.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.vampiresurvivors.ecs.Mappers;
import com.mygdx.vampiresurvivors.ecs.component.PlayerComponent;
import com.mygdx.vampiresurvivors.ecs.component.VelocityComponent;

public class PlayerSystem extends IteratingSystem {

  private static final Family family = Family.all(PlayerComponent.class, VelocityComponent.class ).get();

  private final Vector2 velocity = new Vector2();

  public PlayerSystem() {
    super(family);
  }

  @Override
  public void processEntity(final Entity entity, final float deltaTime) {
    VelocityComponent velocityComponent = Mappers.velocity.get(entity);

    velocityComponent.velocity.set(velocity);

  }

  public void setVelocityX(final float x) {
    this.velocity.x = x;
  }

  public void setVelocityY(final float y) {
    this.velocity.y = y;
  }

  public void setVelocity(final float x, final float y) {
    this.velocity.set(x, y);
  }
}
