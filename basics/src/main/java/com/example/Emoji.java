package com.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Emoji {
    SMILEY("😊"),
    THUMBS_UP("👍"),
    HEART("❤️"),
    SAD("😢"),
    LAUGHING("😂"),
    PARTY("🎉"),
    SUN("☀️"),
    MOON("🌙"),
    STAR("⭐"),
    FIRE("🔥"),
    CHECK_MARK("✅"),
    WAVING_HAND("👋"),
    CAT("🐱"),
    DOG("🐶"),
    PIZZA("🍕"),
    COFFEE("☕"),
    MUSIC("🎵"),
    BOOK("📚"),
    GLASSES("👓"),
    FLOWER("🌸");

    private final String emoji;

     @Override
     public String toString() {
          return emoji;
     }

}

