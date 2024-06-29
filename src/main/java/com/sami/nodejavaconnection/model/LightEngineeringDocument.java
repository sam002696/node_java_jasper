package com.sami.nodejavaconnection.model;



import lombok.Data;

import java.util.List;
@Data
public class LightEngineeringDocument {

    private String reportName;
    private int reportYear;
    private String department;
    private Preface preface;
    private List<String> listOfContents;
    private List<ContentSummary> contentsSummary;
    private List<ConclusionsRecommendations> conclusionsRecommendations;

    @Data
    public static class Preface {
        private List<Paragraph> paragraphs;
        private String date;
        private String author;
        private String role;

        // Getters and setters
        @Data
        public static class Paragraph {
            private String para;

            // Getters and setters
        }
    }

    @Data
    public static class ContentSummary {
        private String contentTitle;
        private List<Paragraph> paragraphs;

        // Getters and setters
        @Data
        public static class Paragraph {
            private String para;

            // Getters and setters
        }
    }

    @Data
    public static class ConclusionsRecommendations {
        private String title;
        private List<Paragraph> paragraphs;
        private List<SectorsSummary> sectorsSummary;

        // Getters and setters

        @Data
        public static class Paragraph {
            private String para;

            // Getters and setters
        }

        @Data
        public static class SectorsSummary {
            private String sectorTitle;
            private String sectorParagraph;
            private List<SectorList> sectorLists;

            // Getters and setters

            @Data
            public static class SectorList {
                private String sectorTitle;
                private String sectorImage;

                // Getters and setters
            }
        }
    }
}
