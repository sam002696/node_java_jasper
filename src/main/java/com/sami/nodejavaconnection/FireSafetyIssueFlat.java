package com.sami.nodejavaconnection;


import lombok.Data;

@Data
public class FireSafetyIssueFlat {
    private String termTitle;
    private String termShortDesc;
    private String recommendation;
    private String important;

    public FireSafetyIssueFlat(String termTitle, String termShortDesc, String recommendation, String important) {
        this.termTitle = termTitle;
        this.termShortDesc = termShortDesc;
        this.recommendation = recommendation;
        this.important = important;
    }
}
