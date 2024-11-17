package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.HashMap;
import java.util.List;
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
        return deathMap.get(currentDying).setCause(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        return false;
    }

    @Override
    public String getDeathCause(String name) {
        return "";
    }

    @Override
    public String getDeathDetails(String name) {
        return "";
    }

    @Override
    public boolean isNameWritten(String name) {
        return false;
    }

    private final static class Death {
        private final static String DEFAULT_CAUSE = "HEART_ATTACK";
        private String cause = DEFAULT_CAUSE;
        private String details;
        private final long initialTime = System.currentTimeMillis();

        public void setDetails(String details) {
            this.details = details;
        }

        public boolean setCause(String cause) {
            if()
            this.cause = cause;
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }
    }
}