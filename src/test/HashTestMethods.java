package edu.commonwealthu.mrodriguez.cmsc230.hashlab.test;

import edu.commonwealthu.mrodriguez.cmsc230.hashlab.Dictionary;

import static edu.commonwealthu.mrodriguez.cmsc230.test.Assert.assertNotNull;
import static edu.commonwealthu.mrodriguez.cmsc230.test.Assert.assertTrue;

/**
 * A class containing unit test methods for HashLab.
 * Only contains a few, to serve as examples.
 */
class HashTestMethods {
    public static final String WORD1 = "cat";
    public static final String WORD1_DEF1 = "an animal of the species felis domesticus";

    static void constructTest() {
        Dictionary d = new Dictionary();
        assertNotNull(d);
    }

    static void addOne() {
        Dictionary d = new Dictionary();
        boolean result = d.add(WORD1, WORD1_DEF1);
        assertTrue(result);
    }

    // STUDENTS: I suggest writing more test methods here, then adding them to the TestSuite.
}
