package com.github.funthomas424242.rades.validation.uservalidations;

/*-
 * #%L
 * rades-annotations
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

import com.github.funthomas424242.rades.SpringTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class})
public class UserValidatorTest {

    @Autowired
    private Validator validator;

    @BeforeEach
    public void prepareTest( ){

    }

    @Test
    @DisplayName("Validiere User mit gültiger eMail und gültigem Login")
    public void testValideEmailValidLogin(){
        User userValid = new User();
        userValid.setEmail("test@test.org");
        userValid.setLogin("testUser");

        Set<ConstraintViolation<User>> violations = validator.validate(userValid);
        Assertions.assertEquals(0,violations.size());

    }


	@Test
	@DisplayName("Validiere User mit gültiger eMail und ungültigem Login")
	public void testValideEmailOhneLogin(){

		final User userInvalid = new User();

		userInvalid.setEmail("@test.com");
		final Set<ConstraintViolation<User>> violations=validator.validate((userInvalid));

		Assertions.assertEquals(1,violations.size());
		Assertions.assertEquals("email",violations.iterator().next().getPropertyPath().iterator().next().getName());

	}

	@Test
	@DisplayName("Validiere User ohne Login und eMail")
	public void testValideOhneLoginUndEmail(){

		final User userInvalid = new User();
		final Set<ConstraintViolation<User>> violations=validator.validate((userInvalid));

		Assertions.assertEquals(1,violations.size());
		Assertions.assertEquals("{username.validation.message}",violations.iterator().next().getMessage());

	}



}
