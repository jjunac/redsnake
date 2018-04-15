package net.taken.redsnake.operations;

import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsList;
import net.taken.redsnake.lang.RedsNull;
import net.taken.redsnake.lang.RedsString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ConversionTableTest {

    static final Conversion<RedsInteger, RedsString> integerToString = new Conversion<>(RedsInteger.TYPE, RedsString.TYPE, x -> new RedsString(Integer.toString(x.getValue())));

    private ConversionTable table;

    @BeforeEach
    void setUp() {
        table = new ConversionTable();
        table.conversions.put(integerToString.getTypeArg(), integerToString.getTypeRes(), integerToString);
    }

    @Test
    void shouldResolveConversionWhenTypesAreMatching() {
        Optional<Conversion<RedsInteger, RedsString>> actual = table.resolveConversion(RedsInteger.TYPE, RedsString.TYPE);
        assertTrue(actual.isPresent());
        assertEquals(integerToString, actual.get());
    }

    @Test
    void shouldNotResolveConversionWhenArgTypeIsNotMatching() {
        Optional<Conversion<RedsList, RedsString>> actual = table.resolveConversion(RedsList.TYPE, RedsString.TYPE);
        assertFalse(actual.isPresent());
    }

    @Test
    void shouldNotResolveConversionWhenResTypeIsNotMatching() {
        Optional<Conversion<RedsInteger, RedsList>> actual = table.resolveConversion(RedsInteger.TYPE, RedsList.TYPE);
        assertFalse(actual.isPresent());
    }

    @Test
    void shouldNotResolveConversionWhenTypesAreNotMatching() {
        Optional<Conversion<RedsList, RedsNull>> actual = table.resolveConversion(RedsList.TYPE, RedsNull.TYPE);
        assertFalse(actual.isPresent());
    }

}
