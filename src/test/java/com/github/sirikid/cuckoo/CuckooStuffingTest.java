/*
 * Copyright (c) 2016, Ivan Sokolov. All rights reserved.
 * This code is licensed under BSD 2-clause license (see LICENSE for details)
 */

package com.github.sirikid.cuckoo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static com.github.sirikid.cuckoo.CuckooStuffing.stuff;
import static com.github.sirikid.cuckoo.CuckooStuffing.unstuff;
import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class CuckooStuffingTest {
	@Parameter(0) public byte[] source;
	@Parameter(1) public byte[] stuffed;

	@Parameters
	public static Object[][] parameters() throws Exception {
		return CuckooExamples.getTestParameters();
	}

	@Test
	public void stuffTest() {
		assertArrayEquals(stuffed, stuff(source));
	}

	@Test
	public void unstuffTest() {
		assertArrayEquals(source, unstuff(stuffed));
	}

	@Test
	public void transitivityTest() {
		assertArrayEquals(source, unstuff(stuff(source)));
		assertArrayEquals(stuffed, stuff(unstuff(stuffed)));
	}

	@Test
	public void stuffTest2() {
		assertArrayEquals(stuff(source), stuff(source, 0, source.length));
	}

	@Test
	public void unstuffTest2() {
		assertArrayEquals(unstuff(stuffed), unstuff(stuffed, 0, stuffed.length));
	}
}
