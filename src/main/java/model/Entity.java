package model;


import java.util.UUID;

public abstract class Entity {
        private UUID id = UUID.randomUUID();

        public UUID getID() {
            return id;
        }

        protected void setID(UUID newID) {
            id = newID;
        }
}
