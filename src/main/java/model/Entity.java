package main.java.model;

import java.util.UUID;

public abstract class Entity {
        private final UUID id = UUID.randomUUID();

        public UUID getId() {
            return id;
        }
}
