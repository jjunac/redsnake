package net.taken.arcanum.parser.visitors;

import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static net.taken.arcanum.parser.ArcanumParser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static net.taken.arcanum.parser.visitors.TestUtils.*;



class ArcanumVisitorTest {

    private ArcanumVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ArcanumVisitor();
    }



}