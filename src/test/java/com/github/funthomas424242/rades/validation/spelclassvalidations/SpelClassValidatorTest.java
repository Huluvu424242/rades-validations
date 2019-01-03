package com.github.funthomas424242.rades.validation.spelclassvalidations;

/*-
 * #%L
 * rades-validations
 * %%
 * Copyright (C) 2018 PIUG
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ValidationException;

@ExtendWith(SpringExtension.class)
public class SpelClassValidatorTest {

	@Test
	@DisplayName("Prüfe vollständig erfasstes Buch per ClassValidation Expression")
	public void erstelleBuchAlleWerteBelegt() {
		final Buch buch = new BuchBuilder()
				.withTitel("Das Wort läuft")
				.withIsbn10("test")
				.withIsbn13("test")
				// Hier wird bereits eine Validierung, daher muss der Kode im Test nicht
				// erneut geschrieben werden - ist aber ein bisl unsauber
				// wir Verlassen uns auf die Generierung des Builders
				.build();

	}

	@Test
	@DisplayName("Prüfe minimal erfasstes Buch per ClassValidation Expression")
	public void erstelleBuchWerteMinimalBelegtISBN10() {
		final Buch buch = new BuchBuilder()
				.withTitel("Das Wort läuft")
				.withIsbn10("test")
				// Hier wird bereits eine Validierung, daher muss der Kode im Test nicht
				// erneut geschrieben werden - ist aber ein bisl unsauber
				// wir Verlassen uns auf die Generierung des Builders
				.build();

	}

	@Test
	@DisplayName("Prüfe minimal erfasstes Buch per ClassValidation Expression")
	public void erstelleBuchWerteMinimalBelegtISBN13() {
		final Buch buch = new BuchBuilder()
				.withTitel("Das Wort läuft")
				.withIsbn13("test")
				// Hier wird bereits eine Validierung, daher muss der Kode im Test nicht
				// erneut geschrieben werden - ist aber ein bisl unsauber
				// wir Verlassen uns auf die Generierung des Builders
				.build();

	}

	@Test
	@DisplayName("Prüfe invalid erfasstes Buch ohne ISBN per ClassValidation Expression")
	public void erstelleInvalidesBuchOhneISBN() {
		Assertions.assertThrows(ValidationException.class, () -> {
			final Buch buch = new BuchBuilder()
					.withTitel("Das Wort läuft")
					// Hier wird bereits eine Validierung, daher muss der Kode im Test nicht
					// erneut geschrieben werden - ist aber ein bisl unsauber
					// wir Verlassen uns auf die Generierung des Builders
					.build();
		});

	}

	@Test
	@DisplayName("Prüfe invalid erfasstes Buch ohne ISBN per ClassValidation Expression")
	public void erstelleInvalidesBuchOhneTitel() {
		Assertions.assertThrows(ValidationException.class, () -> {
			final Buch buch = new BuchBuilder()
					.withIsbn13("test")
					// Hier wird bereits eine Validierung, daher muss der Kode im Test nicht
					// erneut geschrieben werden - ist aber ein bisl unsauber
					// wir Verlassen uns auf die Generierung des Builders
					.build();
		});

	}
}
