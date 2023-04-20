package com.example.jpa_relacje_26_zadaniekoncowe.recipe;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RecipeNameValidator implements ConstraintValidator<RecipeName, String> {

    private int minNameLength;

    @Override
    public void initialize(RecipeName annotation) {
        this.minNameLength = annotation.minNameLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value.contains("_") && value.contains("-")) {
            String[] recipeCodeAndName = value.split("_");
            String code = recipeCodeAndName[0];
            String name = recipeCodeAndName[1];
            return checkCodePart(code) && checkNamePart(name);
        } else {
            return false;
        }
    }

    private boolean checkNamePart(String name) {
        return name.length() >= minNameLength;
    }

    private boolean checkCodePart(String code) {
        String[] codeParts = code.split("-");
        String codePart1 = codeParts[0];
        String codePart2 = codeParts[1];
        int codePart1Length = codePart1.length();
        int codePart2Length = codePart2.length();
        return checkIfCodePartContainDigits(codePart1)
                && checkIfCodePartContainDigits(codePart2)
                && codePart1Length == 2
                && codePart2Length == 3;
    }

    private boolean checkIfCodePartContainDigits(String codePart) {
        char[] chars = codePart.toCharArray();
        for (char codeChar : chars) {
            if (!Character.isDigit(codeChar)) {
                return false;
            }
        }
        return true;
    }
}
