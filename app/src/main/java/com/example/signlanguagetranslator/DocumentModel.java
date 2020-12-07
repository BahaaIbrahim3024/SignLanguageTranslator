package com.example.signlanguagetranslator;

public class DocumentModel {
    private String description;
    private String tag;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    private String video_id;

    public DocumentModel(){

    }


    public DocumentModel(String description, String tag, String video_id){
        this.description=description;
        this.tag=tag;
        this.video_id=video_id;

    }

}
