package com.arttttt.heroeslist.ui.adapter.delegate

import android.widget.TextView
import com.arttttt.heroeslist.R
import com.arttttt.heroeslist.ui.adapter.model.HeroAdapterItem
import com.arttttt.recyclerview.ListItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun HeroAdapterDelegate(
    onClick: (Int) -> Unit
) = adapterDelegate<HeroAdapterItem, ListItem>(R.layout.item_hero) {

    itemView.setOnClickListener {
        onClick.invoke(item.id)
    }

    val heroName = findViewById<TextView>(R.id.hero_name)

    bind {
        heroName.text = item.name
    }

}
