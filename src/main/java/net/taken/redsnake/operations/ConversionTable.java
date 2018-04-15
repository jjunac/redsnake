package net.taken.redsnake.operations;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.Optional;

public class ConversionTable {

    @VisibleForTesting
    Table<Type<? extends RedsObject>, Type<? extends RedsObject>, Conversion> conversions;

    public ConversionTable() {
        this.conversions = HashBasedTable.create();
    }

    public <T extends RedsObject, R extends RedsObject> void registerConversion(Conversion<T, R> conversion) {
        conversions.put(conversion.getTypeArg(), conversion.getTypeRes(), conversion);
    }

    public <T extends RedsObject, R extends RedsObject> Optional<Conversion<T, R>> resolveConversion(Type<T> typeArg, Type<R> typeRes) {
        return Optional.ofNullable(conversions.get(typeArg, typeRes));
    }

}
