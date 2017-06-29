package com.adamzfc.architecturecomponentsdemo.util;

public final class Preconditions {
    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param errorMessage the exception message to use if the check fails
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param errorMessageTemplate a template for the exception message should the check fail. The
     *     message is formed by replacing the single {@code %s} placeholder in the template with
     *     {@code errorMessageArg}.
     * @param errorMessageArg the argument to be substituted into the message template. Converted to a
     *     string using {@link String#valueOf(Object)}.
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     * @throws IllegalArgumentException if {@code errorMessageTemplate} doesn't contain exactly one
     *     "%s"
     */
    public static <T> T checkNotNull(
            T reference, String errorMessageTemplate, Object errorMessageArg) {
        if (reference == null) {
            // Poor-persons version of String.format, which is not GWT-compatible
            if (!errorMessageTemplate.contains("%s")) {
                throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
            }
            if (errorMessageTemplate.indexOf("%s") != errorMessageTemplate.lastIndexOf("%s")) {
                throw new IllegalArgumentException(
                        "errorMessageTemplate has more than one format specifier");
            }
            throw new NullPointerException(
                    errorMessageTemplate.replaceFirst("%s", String.valueOf(errorMessageArg)));
        }
        return reference;
    }

    private Preconditions() {}
}
