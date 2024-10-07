package Model;

import java.util.UUID;

public abstract class ID {
        private final UUID id = UUID.randomUUID();

        public UUID getId() {
            return id;
        }
}
