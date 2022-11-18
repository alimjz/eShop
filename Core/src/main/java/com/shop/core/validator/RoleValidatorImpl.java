package com.shop.core.validator;

import com.shop.core.model.entity.Role;
import com.shop.core.validator.annotation.RoleValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidatorImpl implements ConstraintValidator<RoleValidation, Role> {


    @Override
    public void initialize(RoleValidation constraintAnnotation) {
        constraintAnnotation.role();
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        switch (value) {
            case NORMAL:
            case SUPERADMIN:
            case ADMIN:
                return true;
            default:
                return false;
        }
    }
}
