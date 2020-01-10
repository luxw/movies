# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep SourceFile names & Line Numbers for stack traces. (Note: If we are really security concious,
# we should remove this line.
-keepattributes SourceFile,LineNumberTable

-keepattributes InnerClasses,Signature,*Annotation*,EnclosingMethod

# Keep the names of our models so that Moshi can use them
-keepclassmembers class com.example.currencyconverter.core.rates.domain.** {
  <init>(...);
  <fields>;
}

-keepattributes *Annotation*