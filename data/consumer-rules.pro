# Keep model classes for serialization/deserialization
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep Retrofit interfaces
-keep interface retrofit2.** { *; }
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

# Keep Room database entities and DAOs
-keep class androidx.room.Entity
-keep @androidx.room.* class * { *; }
-keepclassmembers class * {
    @androidx.room.* <fields>;
}

# Keep Kotlin data classes (for serialization)
-keepclassmembers class * {
    <init>(...);
}

# Keep all classes with @Keep annotation
-keep @androidx.annotation.Keep class * {*;}
-keep @androidx.annotation.Keep class * { *; }

# Add additional rules as needed for your project
