package it.unibo.deathnote;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDeathNote {
    DeathNote book = new DeathNoteImplementation();

    @Test
    public void testgetRules() {
        try {
            book.getRule(0);
            fail("Expected exception on 0 input");
        } catch (IllegalArgumentException e) {
            e.getCause();
            assertNotNull(e);
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        } catch (Exception e) {
            fail("Not the expected exception");
        }

        try {
            book.getRule(-1);
            fail("Expected exception on -1 input");
        } catch (IllegalArgumentException e) {
            e.getCause();
            assertNotNull(e);
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        } catch (Exception e) {
            fail("Not the expected exception");
        }
    }

    @Test
    public void testRulesNotBlank() {
        for (var rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    public void testWriteName() {
        final String human = "human";
        assertFalse(book.isNameWritten(human));
        book.writeName(human);
        assertTrue(book.isNameWritten(human));
        assertFalse(book.isNameWritten("Pablo"));
        assertFalse(book.isNameWritten(""));
    }

    @Test
    public void testWritingCauseOfDeath1() throws InterruptedException {
        checkWritingWithoutAName();
        book.writeName("Mario");
        assertEquals(book.getDeathCause("Mario"), DeathNote.DEFAULT_DEATH_CAUSE);
        book.writeName("Sandro");
        assertTrue(book.writeDeathCause("Karting accident"));
        assertEquals(book.getDeathCause("Sandro"), "Karting accident");
        sleep(100);
        assertFalse(book.writeDeathCause("Karting accident"));
        assertEquals(book.getDeathCause("Sandro"), DeathNote.DEFAULT_DEATH_CAUSE);
    }

    @Test
    public void testWritingCauseOfDeath2() throws InterruptedException {
        checkWritingWithoutAName();
        book.writeName("Mario");
        assertNull(book.getDeathDetails("Mario"));
        assertTrue(book.writeDetails("ran for too long"));
        assertEquals(book.getDeathDetails("Mario"), "ran for too long");
        book.writeName("Sandro");
        sleep(6100);
        assertFalse(book.writeDetails("diarrhea"));
        assertNull(book.getDeathDetails("Sandro"));
    }

    private void checkWritingWithoutAName() {
        try{
            book.writeDeathCause("Mario");
            fail("Trying writing cause of deasth");
        } catch (IllegalArgumentException ignored) {
        }
    }
}