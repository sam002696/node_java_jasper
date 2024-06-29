package com.sami.nodejavaconnection.model;


import lombok.Data;
import java.util.List;

@Data
public class LightEngineeringPhotos {
    private List<Image> leImages;

    @Data
    public static class Image {
        private String url;
        private String title;
    }
}
