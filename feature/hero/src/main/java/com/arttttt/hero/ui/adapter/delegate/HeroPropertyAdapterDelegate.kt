package com.arttttt.hero.ui.adapter.delegate

import com.arttttt.hero.R
import com.arttttt.hero.ui.adapter.model.HeroPropertyAdapterItem
import com.arttttt.recyclerview.ListItem
import com.google.android.material.textview.MaterialTextView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun HeroPropertyAdapterDelegate() = adapterDelegate<HeroPropertyAdapterItem, ListItem>(R.layout.item_hero_property) {

    val titleTextView = findViewById<MaterialTextView>(R.id.title_text_view)
    val propertyTextView = findViewById<MaterialTextView>(R.id.property_text_view)

    bind {
        titleTextView.text = item.title
        propertyTextView.text = item.property
    }

}
