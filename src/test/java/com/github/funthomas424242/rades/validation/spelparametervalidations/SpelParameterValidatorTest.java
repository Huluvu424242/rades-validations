package com.github.funthomas424242.rades.validation.spelparametervalidations;

/*-
 * #%L
 * rades-validations
 * %%
 * Copyright (C) 2018 - 2019 PIUG
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import com.github.funthomas424242.rades.validation.spelclassvalidations.Buch;
import com.github.funthomas424242.rades.validation.spelclassvalidations.BuchBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SpelParameterValidatorTest {

	private Buch buch;

	@BeforeEach
	public void testSetUp() {
		buch = new BuchBuilder()
				.withTitel("Kurzes Leben, Langes Sterben")
				.withIsbn13("9783924624040")
				.build();
	}

	@Test
	public void pruefeValidenAufruf() {
		final Ausleihe ausleihe = new AusleiheBuilder()
				.withBuch(buch)
				.withVon(LocalDate.of(2018,12,31))
				.withBis(LocalDate.of(2019,02,01))
				.build();

	}
}
