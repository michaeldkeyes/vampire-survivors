package com.mygdx.vampiresurvivors.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.vampiresurvivors.ecs.Mappers;
import com.mygdx.vampiresurvivors.ecs.component.TransformComponent;
import com.mygdx.vampiresurvivors.ecs.component.VelocityComponent;

public class MovementSystem extends IteratingSystem {

  public MovementSystem() {
    super(Family.all(TransformComponent.class, VelocityComponent.class).get());
  }

  @Override
  public void processEntity(final Entity entity, final float deltaTime) {
    final TransformComponent transformComponent = Mappers.position.get(entity);
    final VelocityComponent velocityComponent = Mappers.velocity.get(entity);

    velocityComponent.velocity.nor();
    velocityComponent.velocity.scl(velocityComponent.speed);
    transformComponent.position.mulAdd(velocityComponent.velocity, deltaTime);
  }
}
