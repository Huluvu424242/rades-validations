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

import com.github.funthomas424242.rades.validation.uservalidations.ValidUsername;

import javax.validation.constraints.Email;

@ValidUsername
public class User {


    private String login;

    @Email
    private String email;

    public String getLogin() {
        return this.login;
    }

    public String getEmail(){
        return  this.email;
    }

    public void setEmail(final String email) {
        this.email=email;
    }

    public void setLogin(final String login) {
        this.login=login;
    }

    // private Set<Car> cars;


}
