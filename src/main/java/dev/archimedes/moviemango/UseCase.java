package dev.archimedes.moviemango;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/// The `UseCase` annotation is a custom stereotype annotation used to mark a class as a use case service in the application.
///
/// This annotation combines the functionality of the `@Service` and `@Transactional` annotations, indicating that the
/// annotated class is a Spring service component and that its methods should be executed within a transactional context.
///
/// Usage of this annotation helps in clearly defining the use case layer in the application architecture, promoting
/// separation of concerns and enhancing code readability and maintainability.
///
/// @see org.springframework.stereotype.Service
/// @see org.springframework.transaction.annotation.Transactional
@Service
@Transactional
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
}
