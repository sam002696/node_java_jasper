package com.sami.nodejavaconnection;

import lombok.Data;
import lombok.Getter;
import java.util.List;

@Data
public class Document {
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
        private List<TermRecommendation> termRecommendation;

        public List<TermRecommendation> getTermRecommendation() {
            return termRecommendation;
        }

    }

    @Data
    public static class TermRecommendation {
        private String _id;
        private String recommendation;
        private String important;

        public String getRecommendation() {
            return recommendation;
        }

        public String getImportant() {
            return important;
        }
    }
}
