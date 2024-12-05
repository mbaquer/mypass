package com.mypass.utils;

import java.security.SecureRandom;

public class PasswordGenerator {
    private final StringBuilder password;
    private final SecureRandom random;
    private final int length;
    private final boolean useLower;
    private final boolean useUpper;
    private final boolean useDigits;
    private final boolean useSpecial;

    public static class Builder {
        private int length = 8;
        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean useSpecial;

        public Builder withLength(int length) {
            this.length = length;
            return this;
        }

        public Builder withLower() {
            this.useLower = true;
            return this;
        }

        public Builder withUpper() {
            this.useUpper = true;
            return this;
        }

        public Builder withDigits() {
            this.useDigits = true;
            return this;
        }

        public Builder withSpecial() {
            this.useSpecial = true;
            return this;
        }

        public PasswordGenerator build() {
            return new PasswordGenerator(this);
        }
    }

    private PasswordGenerator(Builder builder) {
        this.length = builder.length;
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
        this.useSpecial = builder.useSpecial;
        this.password = new StringBuilder();
        this.random = new SecureRandom();
        generatePassword();
    }

    private void generatePassword() {
        String charSet = "";
        if (useLower) {
            charSet += "abcdefghijklmnopqrstuvwxyz";
        }
        if (useUpper) {
            charSet += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if (useDigits) {
            charSet += "0123456789";
        }
        if (useSpecial) {
            charSet += "!@#$%^&*()_-+=<>?";
        }

        for (int i = 0; i < length; i++) {
            password.append(charSet.charAt(random.nextInt(charSet.length())));
        }
    }

    public String getPassword() {
        return password.toString();
    }
}