package com.mygdx.vampiresurvivors.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.mygdx.vampiresurvivors.ecs.Mappers;
import com.mygdx.vampiresurvivors.ecs.component.PositionComponent;
import com.mygdx.vampiresurvivors.ecs.component.VelocityComponent;

public class MovementSystem extends IteratingSystem {

  public MovementSystem() {
    super(Family.all(PositionComponent.class, VelocityComponent.class).get());
  }

  @Override
  public void processEntity(final Entity entity, final float deltaTime) {
    final PositionComponent positionComponent = Mappers.position.get(entity);
    final VelocityComponent velocityComponent = Mappers.velocity.get(entity);

    velocityComponent.velocity.nor();
    velocityComponent.velocity.scl(velocityComponent.speed);
    positionComponent.position.mulAdd(velocityComponent.velocity, deltaTime);
  }
}
