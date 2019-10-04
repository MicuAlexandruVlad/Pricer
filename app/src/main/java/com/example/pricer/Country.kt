package com.example.pricer

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import java.io.Serializable

class Country: Serializable {
    var countryName: String = ""
    var countryFlag: Drawable? = null

    companion object {
        fun getCountriesForList(context: Context, countriesResponse: ArrayList<String>): ArrayList<Country> {
            val countries = ArrayList<Country>()

            for (cr in countriesResponse) {
                val country = Country()
                country.countryName = cr
                when (cr) {
                    "Afghanistan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.afghanistan)
                    "Albania" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.albania)
                    "Algeria" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.algeria)
                    "Andorra" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.andorra)
                    "Angola" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.angola)
                    "Antigua and Barbuda" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.antigua_barbuda)
                    "Argentina" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.argentina)
                    "Armenia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.armenia)
                    "Australia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.australia)
                    "Austria" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.austria)
                    "Azerbaijan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.azerbaijan)
                    "The Bahamas" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.bahamas)
                    "Bahrain" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.bahrain)
                    "Bangladesh" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.bangladesh)
                    "Barbados" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.barbados)
                    "Belarus" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.belarus)
                    "Belgium" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.belgium)
                    "Belize" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.belize)
                    "Benin" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.benin)
                    "Bhutan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.bhutan)
                    "Bolivia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.bolivia)
                    "Bosnia and Herzegovina" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.bosnia)
                    "Botswana" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.botswana)
                    "Brazil" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.brazil)
                    "Brunei" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.brunei)
                    "Bulgaria" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.bulgaria)
                    "Burkina Faso" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.burkina)
                    "Burundi" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.burundi)
                    "Cape Verde" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.cape_verde)
                    "Cambodia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.cambodia)
                    "Cameroon" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.cameroon)
                    "Canada" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.canada)
                    "Central African Republic" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.central_african_republic)
                    "Chad" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.chad)
                    "Chile" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.chile)
                    "China" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.china)
                    "Colombia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.colombia)
                    "Comoros" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.comoros)
                    "Congo, Democratic Republic of the Congo" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.democratic_congo)
                    "Congo, Republic of the Congo" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.republic_congo)
                    "Costa Rica" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.costa_rica)
                    "Ivory Coast" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.ivory_coast)
                    "Croatia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.croatia)
                    "Cuba" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.cuba)
                    "Cyprus" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.cyprus)
                    "Czech Republic" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.czech)
                    "Denmark" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.denmark)
                    "Djibouti" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.djibouti)
                    "Dominica" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.dominica)
                    "Dominican Republic" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.dominican_republic)
                    "East Timor" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.east_timor)
                    "Ecuador" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.ecuador)
                    "Egypt" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.egypt)
                    "El Salvador" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.el_salvador)
                    "Equatorial Guinea" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.eq_guinea)
                    "Eritrea" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.eritrea)
                    "Estonia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.estonia)
                    "Swaziland" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.swaziland)
                    "Ethiopia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.ethiopia)
                    "Fiji" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.fiji)
                    "Finland" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.finland)
                    "France" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.france)
                    "Gabon" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.gabon)
                    "The Gambia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.gambia)
                    "Georgia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.georgia)
                    "Germany" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.germany)
                    "Ghana" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.ghana)
                    "Greece" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.greece)
                    "Grenada" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.grenada)
                    "Guatemala" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.guatemala)
                    "Guinea" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.guinea)
                    "Guinea-Bissau" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.guinea_bissau)
                    "Guyana" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.guyana)
                    "Haiti" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.haiti)
                    "Honduras" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.honduras)
                    "Hungary" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.hungary)
                    "Iceland" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.iceland)
                    "India" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.india)
                    "Indonesia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.indonesia)
                    "Iran" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.iran)
                    "Iraq" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.iraq)
                    "Ireland" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.ireland)
                    "Israel" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.israel)
                    "Italy" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.italy)
                    "Jamaica" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.jamaica)
                    "Japan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.japan)
                    "Jordan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.jordan)
                    "Kazakhstan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.kazakhstan)
                    "Kenya" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.kenya)
                    "Kiribati" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.kiribati)
                    "South Korea" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.s_korea)
                    "North Korea" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.n_korea)
                    "Kosovo" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.kosovo)
                    "Kuwait" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.kuwait)
                    "Kyrgyzstan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.kyrgyzstan)
                    "Laos" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.laos)
                    "Latvia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.latvia)
                    "Lebanon" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.lebanon)
                    "Lesotho" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.lesotho)
                    "Liberia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.liberia)
                    "Libya" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.libya)
                    "Lichtenstein" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.licht)
                    "Lithuania" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.lithuania)
                    "Luxembourg" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.luxembourg)
                    "Madagascar" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.madagascar)
                    "Malawi" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.malawi)
                    "Malaysia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.malaysia)
                    "Maldives" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.maldives)
                    "Mali" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.mali)
                    "Malta" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.malta)
                    "Marshall Islands" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.smart_home)
                    "Mauritania" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.mauritania)
                    "Mauritius" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.mauritius)
                    "Mexico" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.mexico)
                    "Micronesia, Federated States of Micronesia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.micronesia)
                    "Moldova" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.moldova)
                    "Monaco" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.monaco)
                    "Mongolia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.mongolia)
                    "Montenegro" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.montenegro)
                    "Morocco" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.morocco)
                    "Mozambique" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.mozambique)
                    "Myanmar" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.myanmar)
                    "Namibia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.namibia)
                    "Nauru" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.nauru)
                    "Nepal" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.nepal)
                    "Netherlands" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.netherlands)
                    "New Zealand" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.nz)
                    "Nicaragua" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.nicaragua)
                    "Niger" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.niger)
                    "Nigeria" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.nigeria)
                    "North Macedonia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.macedonia)
                    "Norway" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.norway)
                    "Oman" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.oman)
                    "Pakistan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.pakistan)
                    "Palau" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.palau)
                    "Panama" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.panama)
                    "New Guinea" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.pg)
                    "Paraguay" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.paraguay)
                    "Peru" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.peru)
                    "Philippines" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.phil)
                    "Poland" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.poland)
                    "Portugal" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.portugal)
                    "Qatar" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.qatar)
                    "Romania" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.romania)
                    "Russia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.russia)
                    "Rwanda" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.rwanda)
                    "Saint Kitts and Nevis" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.saint_kitts)
                    "Saint Lucia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.saint_lucia)
                    "Saint Vincent and the Grenadines" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.saint_vincent)
                    "Samoa" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.samoa)
                    "San Marino" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.san_marino)
                    "Sao Tome and Principe" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.sao_tome)
                    "Saudi Arabia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.saudi_arabia)
                    "Senegal" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.senegal)
                    "Serbia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.serbia)
                    "Seychelles" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.seychelles)
                    "Sierra Leone" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.sierra_leone)
                    "Singapore" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.singapore)
                    "Slovakia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.slovakia)
                    "Slovenia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.slovenia)
                    "Solomon Islands" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.solomon_islands)
                    "Somalia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.somalia)
                    "South Africa" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.south_africa)
                    "Spain" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.spain)
                    "Sri Lanka" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.sri__lanka)
                    "Sudan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.sudan)
                    "South Sudan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.south_sudan)
                    "Suriname" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.suriname)
                    "Sweden" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.sweden)
                    "Switzerland" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.switzerland)
                    "Syria" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.syria)
                    "Taiwan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.taiwan)
                    "Tajikistan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.tajikistan)
                    "Tanzania" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.tanzania)
                    "Thailand" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.thailand)
                    "Togo" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.togo)
                    "Tonga" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.tonga)
                    "Trinidad and Tobago" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.trinidad)
                    "Tunisia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.tunisia)
                    "Turkey" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.turkey)
                    "Turkmenistan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.turkmenistan)
                    "Tuvalu" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.tuvalu)
                    "Uganda" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.uganda)
                    "Ukraine" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.ukraine)
                    "United Arab Emirates" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.united_arab_emirates)
                    "United Kingdom" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.uk)
                    "United States" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.us)
                    "Uruguay" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.uruguay)
                    "Uzbekistan" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.uzbekistan)
                    "Vanuatu" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.vanuatu)
                    "Vatican City" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.vatican_city)
                    "Venezuela" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.venezuela)
                    "Vietnam" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.vietnam)
                    "Yemen" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.yemen)
                    "Zambia" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.zambia)
                    "Zimbabwe" ->
                        country.countryFlag = ContextCompat.getDrawable(context, R.drawable.zimbabwe)
                }

                countries.add(country)
            }

            return countries
        }
    }
}