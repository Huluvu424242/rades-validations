package com.github.funthomas424242.rades.validation;

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

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpELClassValidator implements ConstraintValidator<ValidateClassExpression,Object> {


	protected ExpressionParser parser = new SpelExpressionParser();
	protected Expression parsedExpression;

	@Override
	public  void initialize(ValidateClassExpression constraintAnnotation){
		parsedExpression=parser.parseExpression(constraintAnnotation.value());
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		StandardEvaluationContext spelContext = new StandardEvaluationContext(value);
		return (Boolean) parsedExpression.getValue(spelContext);
	}
}
