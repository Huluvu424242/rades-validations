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
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Set;

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
	public void pruefeValidenAufruf() throws NoSuchMethodException {
		final Ausleihe ausleihe
				= new AusleiheBuilder()
				.withBuch(buch)
				.build();
		Method method = Ausleihe.class.getMethod("geplanteAusleihe", LocalDate.class, LocalDate.class);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		ExecutableValidator executableValidator = factory.getValidator().forExecutables();
		Set<ConstraintViolation<Ausleihe>> violations = executableValidator.validateParameters(
				ausleihe,
				method,
				Lists.list(LocalDate.of(2018, 12, 31), LocalDate.of(2019, 02, 01)).toArray()
		);

		Assertions.assertEquals(0, violations.size());
	}

	@Test
	public void pruefeInvalidenAufruf() throws NoSuchMethodException {
		final Ausleihe ausleihe
				= new AusleiheBuilder()
				.withBuch(buch)
				.build();
		Method method = Ausleihe.class.getMethod("geplanteAusleihe", LocalDate.class, LocalDate.class);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		ExecutableValidator executableValidator = factory.getValidator().forExecutables();
		Set<ConstraintViolation<Ausleihe>> violations = executableValidator.validateParameters(
				ausleihe,
				method,
				Lists.list(LocalDate.of(2019, 12, 31), LocalDate.of(2018, 02, 01)).toArray()
		);

		Assertions.assertEquals(1, violations.size());
	}
}
