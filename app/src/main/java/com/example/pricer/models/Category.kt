package com.example.pricer.models

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.pricer.R
import java.io.Serializable

class Category: Serializable {
    var categoryName: String = ""
    var imageDrawable: Drawable? = null
    var isSubCategory: Boolean = false

    companion object {
        fun initCategories(context: Context): ArrayList<Category> {
            val categories = ArrayList<Category>()
            val list = ArrayList<String>()

            list.add("Smart Home")
            list.add("Pet Supplies")
            list.add("Sports and Outdoors")
            list.add("Baby")
            list.add("Automotive")
            list.add("Arts & Crafts")
            list.add("Beauty & personal care")
            list.add("Computers")
            list.add("Electronics")
            list.add("Women's Fashion")
            list.add("Men's Fashion")
            list.add("Girls' Fashion")
            list.add("Boys' Fashion")
            list.add("Health and Household")
            list.add("Home and Kitchen")
            list.add("Industrial & Scientific")
            list.add("Luggage")
            list.add("Movies & TV")
            list.add("Software")
            list.add("Tools & Home Improvement")
            list.add("Toys and Games")
            list.add("Video Games")

            for (s in list) {
                val category = Category()
                category.categoryName = s
                category.isSubCategory = false

                when (s) {
                    list[0] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.smart_home
                    )
                    list[1] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.pet_supplies
                    )
                    list[2] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.sports_outdoors
                    )
                    list[3] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.baby
                    )
                    list[4] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.automotive
                    )
                    list[5] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.arts
                    )
                    list[6] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.beauty
                    )
                    list[7] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.computer
                    )
                    list[8] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.electronics
                    )
                    list[9] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.women_dress
                    )
                    list[10] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.men_shirt
                    )
                    list[11] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.girl_dress
                    )
                    list[12] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.boy_shirt
                    )
                    list[13] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.health
                    )
                    list[14] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.home
                    )
                    list[15] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.industrial
                    )
                    list[16] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.luggage
                    )
                    list[17] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.movies
                    )
                    list[18] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.software
                    )
                    list[19] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.tools
                    )
                    list[20] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.toys
                    )
                    list[21] -> category.imageDrawable = ContextCompat.getDrawable(context,
                        R.drawable.video_games
                    )
                }
                categories.add(category)
            }

            return categories
        }

        fun initSubCategories(context: Context, categoryName: String): ArrayList<Category> {
            val subCategories = ArrayList<Category>()
            val list = ArrayList<String>()

            when (categoryName) {
                "Smart Home" -> {
                    list.add("Lighting")
                    list.add("Door Locks")
                    list.add("Cameras")
                    list.add("Plugs")
                    list.add("Other Smart Solutions")
                    list.add("Thermostats")
                    list.add("Security Systems")
                    list.add("TV")
                    list.add("Speakers")
                    list.add("Voice Assistants")
                    list.add("Kitchen")
                    list.add("Vacuums")
                    list.add("Printers and PC")
                    list.add("Network and WiFi")

                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.lighting
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.door_lock
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.cctv
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.plug
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.smart_home
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.thermostat
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.security
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context, R.drawable.tv)
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.speaker
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.google_home
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.kitchen
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.vacuum
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.computer
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.wifi
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Pet Supplies" -> {
                    list.add("Dogs")
                    list.add("Cats")
                    list.add("Fish & Aquatic Pets")
                    list.add("Birds")
                    list.add("Horses")
                    list.add("Reptiles & Amphibians")
                    list.add("Small Animals")

                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.dog
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.cat
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.fish
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.bird
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.horse
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.turtle
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.rabbit
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Sports and Outdoors" -> {
                    list.add("Sports and Outdoors")
                    list.add("Outdoor Recreation")
                    list.add("Sports & Fitness")
                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.sports_outdoors
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.swim
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.fitness
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Baby" -> {
                    list.add("Activity & Entertainment")
                    list.add("Apparel & Accessories")
                    list.add("Baby & Toddler Toys")
                    list.add("Baby Care")
                    list.add("Baby Stationery")
                    list.add("Diapering")
                    list.add("Feeding")
                    list.add("Gifts")
                    list.add("Nursery")
                    list.add("Potty Training")
                    list.add("Pregnancy & Maternity")
                    list.add("Safety")
                    list.add("Strollers & Accessories")
                    list.add("Travel Gear")
                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_entertainment
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_apparel
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_toy
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_care
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_stationery
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_diaper
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_feeding
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.gift
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_nursery
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.potty
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.maternity
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.security
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.stroller
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_car_seat
                                )

                        }
                        subCategories.add(subCategory)
                    }
                }

                "Automotive" -> {
                    list.add("Car Care")
                    list.add("Car Electronics & Accessories")
                    list.add("Exterior Accessories")
                    list.add("Interior Accessories")
                    list.add("Lights & Lighting Accessories")
                    list.add("Motorcycle & Powersports")
                    list.add("Oils & Fluids")
                    list.add("Paint & Paint Supplies")
                    list.add("Performance Parts & Accessories")
                    list.add("Replacement Parts")
                    list.add("RV Parts & Accessories")
                    list.add("Tires & Accessories")
                    list.add("Tools & Equipment")
                    list.add("Automotive Merchandise")
                    list.add("Heavy Duty & Commercial Equipment")
                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.car_care
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.charger
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.wheel
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.steering_wheel
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.lighting
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.motorcycle
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.oil
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.palette
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.turbo
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.engine
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context, R.drawable.rv)
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.tire
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.tools
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.boy_shirt
                                )
                            list[14] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.walkie_talkie
                                )

                        }
                        subCategories.add(subCategory)
                    }
                }

                "Arts & Crafts" -> {
                    list.add("Painting, Drawing & Art Supplies")
                    list.add("Beading & Jewelry Making")
                    list.add("Crafting")
                    list.add("Fabric")
                    list.add("Fabric Decorating")
                    list.add("Knitting & Crochet")
                    list.add("Needlework")
                    list.add("Organization, Storage & Transport")
                    list.add("Printmaking")
                    list.add("Scrap booking & Stamping")
                    list.add("Sewing")
                    list.add("Party Decorations & Supplies")
                    list.add("Gift Wrapping Supplies")

                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.paint_palette
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.bead
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.crafting
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.fabric
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.fabric_deco
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.knitting
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.needle
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.storage
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.printmaking
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.stamp
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.sewing
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_entertainment
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.gift
                                )

                        }
                        subCategories.add(subCategory)
                    }
                }

                "Beauty & personal care" -> {
                    list.add("Makeup")
                    list.add("Skin Care")
                    list.add("Hair Care")
                    list.add("Fragrance")
                    list.add("Foot, Hand & Nail Care")
                    list.add("Tools & Accessories")
                    list.add("Shave & Hair Removal")
                    list.add("Personal Care")
                    list.add("Oral Care")

                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.makeup
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.skin_care
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.hair_care
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.fragrance
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.hand
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.hair_dryer
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.razor
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.drop
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.toothbrush
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Computers" -> {
                    list.add("Computer Accessories & Peripherals")
                    list.add("Computer Components")
                    list.add("Computer & Tablets")
                    list.add("Data Storage")
                    list.add("External Components")
                    list.add("Laptop Accessories")
                    list.add("Monitors")
                    list.add("Networking Products")
                    list.add("Power Strips & Surge Protectors")
                    list.add("Printers")
                    list.add("Scanners")
                    list.add("Servers")
                    list.add("Tablet Accessories")
                    list.add("Tablet Replacement Parts")
                    list.add("Warranties & Services")

                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.usb
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.processor
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.devices
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.database
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.pc_case
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.laptop_accessories
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.monitor
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.router
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.power_strip
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.printer
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.scanner
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.server
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.keyboard
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.replacement
                                )
                            list[14] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.warranty
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Electronics" -> {
                    list.add("Accessories & Supplies")
                    list.add("Camera & Photo")
                    list.add("Car & Vehicle Electronics")
                    list.add("Cell Phones & Accessories")
                    list.add("Computers & Accessories")
                    list.add("GSP & Navigation")
                    list.add("Headphones")
                    list.add("Home Audio")
                    list.add("Office Electronics")
                    list.add("Portable Audio & Video")
                    list.add("Security & Surveillance")
                    list.add("Service Plans")
                    list.add("Television & Video")
                    list.add("Video Game Consoles & Accessories")
                    list.add("Video Projectors")
                    list.add("Wearable Technology")
                    list.add("eBook Readers")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.battery
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.camera
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.electronics
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.smartphone
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.computer
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.navigation
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.headphones
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.audio_system
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.printer
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.camera
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.security
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.tools
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context, R.drawable.tv)
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.console
                                )
                            list[14] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.projector
                                )
                            list[15] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.smart_watch
                                )
                            list[16] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.e_book
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Women's Fashion" -> {
                    list.add("Clothing")
                    list.add("Shoes")
                    list.add("Jewelry")
                    list.add("Watches")
                    list.add("Handbags")
                    list.add("Accessories")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.women_dress
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.high_heels
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.jewelry
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.smart_watch
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.handbag
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.glasses
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Men's Fashion" -> {
                    list.add("Clothing")
                    list.add("Shoes")
                    list.add("Watches")
                    list.add("Accessories")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.men_shirt
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.shoe
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.smart_watch
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.belt
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Girls' Fashion" -> {
                    list.add("Clothing")
                    list.add("Shoes")
                    list.add("Jewelry")
                    list.add("Watches")
                    list.add("Accessories")
                    list.add("School Uniforms")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.girl_dress
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.girls_shoes
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.earrings
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.smart_watch
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.hat
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.girl_uniform
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Boys' Fashion" -> {
                    list.add("Clothing")
                    list.add("Shoes")
                    list.add("Accessories")
                    list.add("School Uniforms")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.boy_shirt
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.shoe
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.smart_watch
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.boy_uniform
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Health and Household" -> {
                    list.add("Baby & Child Care")
                    list.add("Health Care")
                    list.add("Household Supplies")
                    list.add("Oral Care")
                    list.add("Personal Care")
                    list.add("Sexual Wellness")
                    list.add("Sports Nutrition")
                    list.add("Stationery & Gift Wrapping Supplies")
                    list.add("Vision Care")
                    list.add("Vitamins & Supplements")
                    list.add("Wellness & Relaxation")

                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_care
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.health
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.roll
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.toothbrush
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.skin_care
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.condoms
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.fork
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_stationery
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.drop
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.vitamin_pill
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.aromatherapy
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Home and Kitchen" -> {
                    list.add("Kids' Home Store")
                    list.add("Kitchen & Dining")
                    list.add("Bedding")
                    list.add("Bath")
                    list.add("Furniture")
                    list.add("Home Decor")
                    list.add("Wall Art")
                    list.add("Lighting & Ceiling Fans")
                    list.add("Event & Party Supplies")
                    list.add("Heating, Cooling & Air Quality")
                    list.add("Irons & Steamers")
                    list.add("Vacuums & Floor Care")
                    list.add("Storage & Organization")
                    list.add("Cleaning Supplies")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.lamp
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.kitchen_dining
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.bed
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.bathtub
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.furniture
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.wall_art
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.wall_art
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.lighting
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.confetti
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context, R.drawable.ac)
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.iron
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.vacuum
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.storage
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.cleaning
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Industrial & Scientific" -> {
                    list.add("Abrasive & Finishing Products")
                    list.add("Additive Manufacturing Products")
                    list.add("Commercial Door Products")
                    list.add("Cutting Tools")
                    list.add("Fasteners")
                    list.add("Filtration")
                    list.add("Food Service Equipment & Supplies")
                    list.add("Hydraulics, Pneumatics & Plumbing")
                    list.add("Industrial Electrical")
                    list.add("Industrial Hardware")
                    list.add("Industrial Power & Hand Tools")
                    list.add("Janitorial & Sanitation Supplies")
                    list.add("Lab & Scientific Products")
                    list.add("Material Handling Products")
                    list.add("Occupational Health & Safety Products")
                    list.add("Packaging & Shipping Supplies")
                    list.add("Power Transmission Supplies")
                    list.add("Professional Dental Supplies")
                    list.add("Professional Medical Supplies")
                    list.add("Raw Materials")
                    list.add("Retail Store Fixtures & Equipment")
                    list.add("Robotics")
                    list.add("Science Education")
                    list.add("Tapes, Adhesives & Sealants")
                    list.add("Test, Measure & Inspect")

                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.sandpaper
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.printing_filament
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.door_products
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.handyman_tools
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.nails
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.filtration
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.paper_towel
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.hose
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.sensor
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.hanger
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.driller
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.paper_roll
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.microscope
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.shelves
                                )
                            list[14] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.ear_plug
                                )
                            list[15] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.duct_tape
                                )
                            list[16] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.gear
                                )
                            list[17] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.professional_dental_care
                                )
                            list[18] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.bandaid
                                )
                            list[19] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.raw_materials
                                )
                            list[20] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.shopping_bag
                                )
                            list[21] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.robotics
                                )
                            list[22] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.book
                                )
                            list[23] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.tape
                                )
                            list[24] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.ruler
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Luggage" -> {
                    list.add("Carry-ons")
                    list.add("Backpacks")
                    list.add("Garment Bags")
                    list.add("Travel Totes")
                    list.add("Luggage Sets")
                    list.add("Laptop Bags")
                    list.add("Suitcases")
                    list.add("Kids Luggage")
                    list.add("Messenger Bags")
                    list.add("Umbrellas")
                    list.add("Sport Bags")
                    list.add("Travel Accessories")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.luggage
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.backpack
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.garment_bag
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.travel_totes
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.travel_sets
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.laptop_bag
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.suitcase
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.kids_luggage
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.messenger_bag
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.umbrella
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.sport_bag
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.travel_accessories
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Movies & TV" -> {
                    list.add("Movies")
                    list.add("TV Shows")
                    list.add("Blu-ray")
                    list.add("Ultra HD")



                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.movie
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.tv_show
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.blu_ray
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.uhd
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Software" -> {
                    list.add("Accounting & Finance")
                    list.add("Antivirus & Security")
                    list.add("Business & Office")
                    list.add("Design & Illustration")
                    list.add("Education")
                    list.add("Games")
                    list.add("Music")
                    list.add("Operating Systems")
                    list.add("Photography")
                    list.add("Programming & Web Development")
                    list.add("Tax Preparation")
                    list.add("Utilities")
                    list.add("Video")



                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.finance
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.security
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.business
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.design
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.education
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.console
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.music
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.windows
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.photography
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.code
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.tax
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.pdf
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.movies
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Tools & Home Improvement" -> {
                    list.add("Tools & Home Improvement")
                    list.add("Appliances")
                    list.add("Building Supplies")
                    list.add("Electrical")
                    list.add("Hardware")
                    list.add("Kitchen & Bath Fixtures")
                    list.add("Light Bulbs")
                    list.add("Lighting & Ceiling Fans")
                    list.add("Measuring & Layout Tools")
                    list.add("Painting Supplies & Wall Treatments")
                    list.add("Power & Hand Tools")
                    list.add("Rough Plumbing")
                    list.add("Safety & Security")
                    list.add("Storage & Home Organization")
                    list.add("Welding & Soldering")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.handyman_tools
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.washing_machine
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.duct_tape
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.plug
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.rope
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.filtration
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.lighting
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.lamp
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.ruler
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.paint_palette
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.driller
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.pipe
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.security
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.shelves
                                )
                            list[14] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.welding
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Toys and Games" -> {
                    list.add("Action Figures & Statues")
                    list.add("Arts & Crafts")
                    list.add("Baby & Toddler Toys")
                    list.add("Building Toys")
                    list.add("Dolls & Accessories")
                    list.add("Dress Up")
                    list.add("Kids' Electronics")
                    list.add("Games")
                    list.add("Grown-up Toys")
                    list.add("Hobbies")
                    list.add("Kids' Furniture, Decor & Storage")
                    list.add("Learning & Education")
                    list.add("Party Supplies")
                    list.add("Puppets")
                    list.add("Puzzles")
                    list.add("Sports & Outdoor Play")
                    list.add("Plush Toys")
                    list.add("Tricycles, Scooters & Wagons")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.statue
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.arts
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.baby_toy
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.block
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.doll
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.dino
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.robotics
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.monopoly
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.casino
                                )
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.rc_car
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.furniture
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.study
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.confetti
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.puppet
                                )
                            list[14] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.puzzle
                                )
                            list[15] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.football
                                )
                            list[16] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.plush_toy
                                )
                            list[17] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.tricycle
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }

                "Video Games" -> {
                    list.add("Video Games")
                    list.add("PlayStation 4")
                    list.add("PlayStation 3")
                    list.add("Xbox One")
                    list.add("Xbox 360")
                    list.add("Nintendo Switch")
                    list.add("Wii U")
                    list.add("Wii")
                    list.add("PC")
                    list.add("Mac")
                    list.add("Nintendo 3DS & 2DS")
                    list.add("Nintendo DS")
                    list.add("PlayStation Vita")
                    list.add("Sony PSP")
                    list.add("Retro Gaming & Microconsoles")
                    list.add("Accessories")
                    list.add("Digital Games")


                    for (s in list) {
                        val subCategory = Category()
                        subCategory.isSubCategory = true
                        subCategory.categoryName = s
                        when (s) {
                            list[0] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.video_games
                                )
                            list[1] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.ps4
                                )
                            list[2] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.ps3
                                )
                            list[3] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.xbox_one
                                )
                            list[4] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.xbox_360
                                )
                            list[5] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.nintendo_switch
                                )
                            list[6] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.wii_u
                                )
                            list[7] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.wii
                                )
                            list[8] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context, R.drawable.pc)
                            list[9] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.mac
                                )
                            list[10] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.nintendo_3ds
                                )
                            list[11] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.nintendo_ds
                                )
                            list[12] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.ps_vita
                                )
                            list[13] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.psp
                                )
                            list[14] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.retro_console
                                )
                            list[15] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.console
                                )
                            list[16] -> subCategory.imageDrawable =
                                ContextCompat.getDrawable(context,
                                    R.drawable.digital_games
                                )
                        }
                        subCategories.add(subCategory)
                    }
                }
            }


            return subCategories
        }
    }
}