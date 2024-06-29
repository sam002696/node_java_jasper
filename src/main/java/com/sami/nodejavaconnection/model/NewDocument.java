package com.sami.nodejavaconnection.model;

import lombok.Data;
import lombok.Getter;
import java.util.List;

@Data
public class NewDocument {
    private String _id;
    private String name;
    private String email;
    private String description;
    private String createdAt;
    private int __v;
    private List<FireSafetyIssue> fireSafetyIssues;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<FireSafetyIssue> getFireSafetyIssues() {
        return fireSafetyIssues;
    }

    @Data
    public static class FireSafetyIssue {
        @Getter
        private String termTitle;
        @Getter
        private String termShortDesc;
        @Getter
        private String normalRecommendation;
        @Getter
        private String seriousRecommendation;
    }



}

