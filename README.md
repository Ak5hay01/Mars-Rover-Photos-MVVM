# Mars-Rover-Photos-MVVM
This is a sample project in which I have used Retrofit to fetch the Mars Rover photos from NASA and bind them to the recycler view. 

I have used following Dependencies

    def nav_version_ktx = "2.3.0-alpha01"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version_ktx"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version_ktx"

    def material_version = "1.1.0"
    implementation "com.google.android.material:material:$material_version"

    def fragment_version = "1.2.4"
    implementation "androidx.fragment:fragment-ktx:$fragment_version"

    def cardview_version = "1.0.0"
    implementation "androidx.cardview:cardview:$cardview_version"

    def glide_version = "4.9.0"
    implementation "com.github.bumptech.glide:glide:$glide_version"
    implementation "com.github.bumptech.glide:okhttp3-integration:$glide_version"
    kapt "com.github.bumptech.glide:compiler:$glide_version"

    def retrofit_version = "2.6.0"
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"

    def coroutines_version = "1.3.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

    def view_model_live_data_version = "2.2.0"
    implementation "androidx.lifecycle:lifecycle-extensions:$view_model_live_data_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$view_model_live_data_version"
