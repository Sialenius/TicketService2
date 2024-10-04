package Model;

import java.util.UUID;

public interface ID {
        UUID id = UUID.randomUUID();

        public default UUID getId() {
            return id;
        }
}
