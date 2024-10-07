package Model;

import java.util.UUID;

public abstract class ID {
        UUID id = UUID.randomUUID();

        public UUID getId() {
            return id;
        }
}
