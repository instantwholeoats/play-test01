package controllers;

import play.data.validation.Constraints;

public class SampleForm {
    @Constraints.Required
    @Constraints.MaxLength(255)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
