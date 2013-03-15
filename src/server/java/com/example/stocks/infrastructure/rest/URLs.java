package com.example.stocks.infrastructure.rest;

import java.net.URL;

import static com.googlecode.totallylazy.Strings.EMPTY;
import static com.googlecode.totallylazy.URLs.url;

public class URLs {

    public static URL defaultPackageUrl(final Class<?> aClass) {
        String name = aClass.getSimpleName() + ".class";
        String url = aClass.getResource(name).toString();
        url = url.replace(aClass.getPackage().getName().replace(".", "/"), EMPTY);
        url = url.replace(name, EMPTY);
        url = url.replace("//", "/");
        return url(url);
    }
}
