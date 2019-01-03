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

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class SpelParameterValidator implements ConstraintValidator<ValidateParametersExpression, Object[]> {


	private ExpressionParser parser = new SpelExpressionParser();
	private Expression parsedExpresssion;


	@Override
	public void initialize(ValidateParametersExpression constraintAnnotation) {
		parsedExpresssion = parser.parseExpression(constraintAnnotation.value());
	}

	@Override
	public boolean isValid(Object[] values, ConstraintValidatorContext context) {
		StandardEvaluationContext spelContext = new StandardEvaluationContext(values);
		Map<String,Object> spelVars = IntStream.range(0,values.length).boxed()
				.collect(Collectors.toMap(i->"arg"+i, i->values[i]));
		spelContext.setVariables(spelVars);
		return (Boolean) parsedExpresssion.getValue(spelContext);
	}
}
