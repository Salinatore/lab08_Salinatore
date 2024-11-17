package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeathNoteImplementation implements DeathNote {

    private final Map<String, Death> deathMap = new HashMap<>();
    private String currentDying;

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > DeathNote.RULES.size()) {
            throw new IllegalArgumentException("RuleNumber required positive");
        }
        return DeathNote.RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        Objects.requireNonNull(name);
        currentDying = name;
        deathMap.put(name, new Death());
    }

    @Override
    public boolean writeDeathCause(String cause) {
        checkString(cause);
        return deathMap.get(currentDying).setCause(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        checkString(details);
        return deathMap.get(currentDying).setDetails(details);
    }

    @Override
    public String getDeathCause(String name) {
        checkName(name);
        return deathMap.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        checkName(name);
        return deathMap.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return deathMap.containsKey(name);
    }

    private void checkString(String string) {
        if(string == null) {
            throw new IllegalArgumentException("Name must be not null");
        }
        if(currentDying == null) {
            throw new IllegalArgumentException("A string must be in Set");
        }
    }

    private void checkName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name must be not null");
        }
        if(!deathMap.containsKey(name)) {
            throw new IllegalArgumentException("Name not contained in Set");
        }
    }

    private final static class Death {
        private final static String DEFAULT_CAUSE = "HEART_ATTACK";
        private final static String DEFAULT_DETAILS = null;
        public static final int MAX_TIME_WRITE_CAUSE = 40;
        public static final int MAX_TIME_WRITE_DETAILS = 6040;
        private String cause;
        private String details;
        private final long initialTime;

        public Death() {
            this.cause = DEFAULT_CAUSE;
            this.details = DEFAULT_DETAILS;
            initialTime = System.currentTimeMillis();
        }

        public boolean setCause(String cause) {
            if(System.currentTimeMillis() < (initialTime + MAX_TIME_WRITE_CAUSE)){
                this.cause = cause;
                return true;
            } else {
                return false;
            }
        }

        public boolean setDetails(String details) {
            if(System.currentTimeMillis() < (initialTime + MAX_TIME_WRITE_DETAILS)){
                this.details = details;
                return true;
            } else {
                return false;
            }
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }
    }
}