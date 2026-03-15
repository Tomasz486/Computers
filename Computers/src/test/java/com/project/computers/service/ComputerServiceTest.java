package com.project.computers.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.project.computers.repository.ComputerRepository;

@ExtendWith(MockitoExtension.class)
class ComputerServiceTest {

	@Mock
	private DataInitializer dataInitializer;

	@Mock
	private ComputerRepository computerRepository;

	@Mock
	private NbpService nbpService;

	@Mock
	private ComputerXmlService computerXmlService;

	@InjectMocks
	private ComputerService computerService;

	@Test
	void shouldCallFindAllWhenNoFiltersProvided() throws Exception {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		when(computerRepository.findAll(pageable)).thenReturn(Page.empty());

		// when
		computerService.GetComputers(null, null, pageable);

		// then
		verify(computerRepository).findAll(pageable);
		verify(dataInitializer).initDatabase(any(), any(), any());
	}

	@Test
	void shouldFilterByNameOnly() throws Exception {
		// given
		String name = "Dell";
		Pageable pageable = PageRequest.of(0, 10);
		when(computerRepository.findByNameContainingIgnoreCase(eq(name), any())).thenReturn(Page.empty());

		// when
		computerService.GetComputers(name, null, pageable);

		// then
		verify(computerRepository).findByNameContainingIgnoreCase(name, pageable);
	}
}