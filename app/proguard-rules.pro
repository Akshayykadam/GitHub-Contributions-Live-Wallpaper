# ========== CodeCalendar ProGuard Rules ==========

# --- Keep Hilt-generated components ---
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# --- Keep Room entities & DAOs ---
-keep class com.githubwallpaper.data.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }

# --- Keep Retrofit service interfaces ---
-keep,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn retrofit2.**
-dontwarn okhttp3.**
-dontwarn okio.**

# --- Keep Moshi adapters ---
-keep class com.squareup.moshi.** { *; }
-keepclassmembers class * {
    @com.squareup.moshi.Json <fields>;
}

# --- Keep WallpaperService ---
-keep class com.githubwallpaper.wallpaper.** { *; }

# --- Keep SettingsActivity & Application ---
-keep class com.githubwallpaper.ui.** { *; }
-keep class com.githubwallpaper.WallpaperApplication { *; }

# --- Keep WorkManager workers ---
-keep class com.githubwallpaper.worker.** { *; }

# --- General ---
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes EnclosingMethod
