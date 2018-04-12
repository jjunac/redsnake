package net.taken.redsnake.interpretor;

import lombok.Data;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

@Data
public class Value<T extends RedsObject> {
    private final Type<T> type;
    private final T value;
}
