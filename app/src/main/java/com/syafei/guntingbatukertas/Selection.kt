package com.syafei.guntingbatukertas

enum class Selection (val selection: Int){
    DEFAULT(-1),
    ROCK(0),
    PAPER(1),
    SCISSOR(2);

    companion object{
        fun fromSelection(select: Int): Selection{
            val values = Selection.values()
            val result = values.find {
                val isEqual = it.selection == select
                return@find isEqual
            }
            return result ?: DEFAULT
        }
    }
}