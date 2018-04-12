package net.taken.redsnake.interpretor;

import lombok.Data;
import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.lang.RedsString;
import net.taken.redsnake.reflect.Type;

@Data(staticConstructor = "create")
public class Value<T extends RedsObject> {
    private final Type<T> type;
    private final T value;

    public static <E extends RedsObject> Value<E> of(E redsObject) {
        return new Value<E>(redsObject.getType(), redsObject);
    }

    public static Value<RedsInteger> ofInteger(Integer integer) {
        return of(new RedsInteger(integer));
    }

    public static Value<RedsString> ofString(String str) {
        return of(new RedsString(str));
    }

    public static Value<RedsBoolean> ofBoolean(boolean bool) {
        return of(new RedsBoolean(bool));
    }

}
