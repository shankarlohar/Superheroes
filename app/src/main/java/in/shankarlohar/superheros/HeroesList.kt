package `in`.shankarlohar.superheros

import `in`.shankarlohar.superheros.model.Hero
import `in`.shankarlohar.superheros.model.HeroesRepository
import `in`.shankarlohar.superheros.ui.theme.SuperherosTheme
import `in`.shankarlohar.superheros.ui.theme.Typography
import android.content.res.Configuration
import android.graphics.Paint.Style
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
){
    LazyColumn{
            items(heroes){
                HeroListItem(
                    hero = it,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

        }
    }
}

@Composable
fun HeroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
){
    Card (
        elevation = 2.dp,
        modifier = modifier
            ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
                ) {
            Column (modifier = Modifier.weight(1f)) {
                Text(text = stringResource(hero.nameRes), style = MaterialTheme.typography.h3)
                Text(text = stringResource(hero.descriptionRes), style = MaterialTheme.typography.body1)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box (
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
                    ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = stringResource(hero.nameRes),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview("Heroes List")
@Composable
fun HeroesPreview() {
    SuperherosTheme(darkTheme = false) {
        Surface (
            color = MaterialTheme.colors.background
        ) {
            /* Important: It is not a good practice to access data source directly from the UI.
            In later units you will learn how to use ViewModel in such scenarios that takes the
            data source as a dependency and exposes heroes.
            */
            HeroesList(heroes = HeroesRepository.heroes)
        }
    }
}


@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroPreview() {
    val hero = Hero(
        R.string.hero1,
        R.string.description1,
        R.drawable.android_superhero1
    )
    SuperherosTheme {
        HeroListItem(hero = hero)
    }
}