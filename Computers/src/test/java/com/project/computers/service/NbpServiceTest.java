package com.project.computers.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NbpServiceTest {

	private final NbpService nbpService = new NbpService();

	@Test
	void shouldKeepWeekdayUnchanged() {
		LocalDate result = nbpService.adjustToPreviousWorkingDay(LocalDate.of(2026, 1, 12));
		assertEquals(LocalDate.of(2026, 1, 12), result);
	}

	@Test
	void shouldMoveSaturdayToFriday() {
		LocalDate result = nbpService.adjustToPreviousWorkingDay(LocalDate.of(2026, 1, 10));
		assertEquals(LocalDate.of(2026, 1, 9), result);
	}

	@Test
	void shouldMoveSundayToFriday() {
		LocalDate result = nbpService.adjustToPreviousWorkingDay(LocalDate.of(2026, 1, 11));
		assertEquals(LocalDate.of(2026, 1, 9), result);
	}
}