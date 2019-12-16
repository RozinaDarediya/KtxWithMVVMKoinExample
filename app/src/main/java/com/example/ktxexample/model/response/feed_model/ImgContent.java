package com.example.ktxexample.model.response.feed_model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "content", strict = false)
public class ImgContent {

    @Attribute(name = "url")
    public String url;
}
