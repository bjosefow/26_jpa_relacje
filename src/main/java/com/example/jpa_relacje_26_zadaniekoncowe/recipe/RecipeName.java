package com.example.jpa_relacje_26_zadaniekoncowe.recipe;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
//ze odnosi sie to do ograniczen
@Constraint(validatedBy = RecipeNameValidator.class)
// czego ma dotyczyc te walidacja: pol klasy, paramtery metod itd.
@Target({FIELD})
//zeby nasze adnotacje nie zniknely po komilacji a istnialy tez w fazie wykonania
@Retention(RUNTIME)
public @interface RecipeName {
    String message() default "{pl.javastart.constraint.RecipeName.message}";
    // grupy sa po to, jesli nie bedziemy chcieli walidowac wszytskich pol jakiegos dto jednoczesnie np.
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minNameLength() default 3;
}
