package com.example.praja.myblog;

import android.support.annotation.NonNull;

public class BlogCommentId {

    public String BlogCommentId;

    public <T extends BlogCommentId> T withId(@NonNull final String id) {
        this.BlogCommentId = id;
        return (T) this;
    }

}
