package com.robertomr99.rickandmortycharacters.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Utils {
    companion object{

        fun obtainNumFromURLs(urls: List<String>): String {
            val num = mutableListOf<String>()

            for (url in urls) {
                val patron = "\\d+$" // Busca uno o más dígitos al final de la cadena
                val regex = Regex(patron)
                val coincidencias = regex.findAll(url)
                val listaNumeros = coincidencias.map { it.value }.toList()

                if (listaNumeros.isNotEmpty()) {
                    num.addAll(listaNumeros)
                }
            }

            if (num.size > 1) {
                val ultimoNumero = num.last()
                num[num.size - 1] = ultimoNumero.substringBeforeLast(",")
            }

            return num.joinToString(", ")
        }

         fun hideKeyboard(context: Context, view : View){
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

        }
    }

}