package com.example.javajokesproviderlibrary;

import java.util.Random;

public class Jokes {

    final static String[] JOKES = new String[]{
            "Today at the bank, an old lady asked me to help check her balance. So I pushed her over.",
            "I bought some shoes from a drug dealer. I don't know what he laced them with, but I've been tripping all day.",
            "I told my girlfriend she drew her eyebrows too high. She seemed surprised.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "I'm so good at sleeping. I can do it with my eyes closed.",
            "My boss told me to have a good day.. so I went home.",
            "A woman walks into a library and asked if they had any books about paranoia. The librarian says : They're right behind you!",
            "The other day, my wife asked me to pass her lipstick but I accidentally passed her a glue stick. She still isn't talking to me."
    };

    public static String getJokes() {
        return JOKES[new Random().nextInt(JOKES.length)];
    }
}
