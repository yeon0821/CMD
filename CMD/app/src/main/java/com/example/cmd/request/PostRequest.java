package com.example.cmd.request;

public class PostRequest {
    private String title;
    private String contents;

    public PostRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
